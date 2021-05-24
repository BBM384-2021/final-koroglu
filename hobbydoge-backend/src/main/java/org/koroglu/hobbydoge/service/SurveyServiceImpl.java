package org.koroglu.hobbydoge.service;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.AddSurveyRequest;
import org.koroglu.hobbydoge.controller.request.AnswerRequest;
import org.koroglu.hobbydoge.controller.request.NewSurveyAnswerRequest;
import org.koroglu.hobbydoge.controller.request.QuestionRequest;
import org.koroglu.hobbydoge.dto.mapper.SurveyPointMapper;
import org.koroglu.hobbydoge.dto.mapper.SurveyQuestionMapper;
import org.koroglu.hobbydoge.dto.model.SurveyPointDTO;
import org.koroglu.hobbydoge.dto.model.SurveyQuestionDTO;
import org.koroglu.hobbydoge.exception.RestClubDoesNotExistException;
import org.koroglu.hobbydoge.exception.RestClubSurveyAlreadyExistException;
import org.koroglu.hobbydoge.exception.RestSurveyPointDoesNotExistException;
import org.koroglu.hobbydoge.exception.RestSurveyQuestionDoesNotExistException;
import org.koroglu.hobbydoge.model.Club;
import org.koroglu.hobbydoge.model.SurveyPoint;
import org.koroglu.hobbydoge.model.SurveyQuestion;
import org.koroglu.hobbydoge.model.User;
import org.koroglu.hobbydoge.repository.ClubRepository;
import org.koroglu.hobbydoge.repository.SurveyPointRepository;
import org.koroglu.hobbydoge.repository.SurveyQuestionRepository;
import org.koroglu.hobbydoge.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class SurveyServiceImpl implements SurveyService {

  private final SurveyQuestionRepository surveyQuestionRepository;
  private final ClubRepository clubRepository;
  private final SurveyPointRepository surveyPointRepository;

    @Override
  public List<SurveyQuestionDTO> getSurvey(Long clubId) {
    return surveyQuestionRepository.findSurveyQuestionsByClub_Id(clubId).stream().map(questions -> {
              System.out.println(questions);
              return SurveyQuestionMapper.toSurveyQuestionDTO(questions);
            }
    ).collect(Collectors.toList());
  }

    @Override
    public List<SurveyQuestionDTO> createSurvey(AddSurveyRequest addSurveyRequest) {

        List<SurveyQuestionDTO> response= new ArrayList<>();
        Optional<Club> optionalClub=clubRepository.findById(addSurveyRequest.getClubId());
        if(!optionalClub.isPresent()){
            throw new RestClubDoesNotExistException();
        }
        if (surveyQuestionRepository.findSurveyQuestionsByClub_Id(optionalClub.get().getId()).size()!=0){
            throw new RestClubSurveyAlreadyExistException();
        }
        for (QuestionRequest questionRequest :addSurveyRequest.getQuestionRequests()
             ) {
            SurveyQuestion surveyQuestion = new SurveyQuestion(
                    questionRequest.getQuestion(),
                    questionRequest.isIncrease(),
                    optionalClub.get()
            );
            surveyQuestionRepository.save(surveyQuestion);
            response.add(SurveyQuestionMapper.toSurveyQuestionDTO(surveyQuestion));
        }

        return response;
    }

    @Override
    public List<SurveyQuestionDTO> updateSurvey(AddSurveyRequest addSurveyRequest) {

        List<SurveyQuestionDTO> response= new ArrayList<>();
        Optional<Club> optionalClub=clubRepository.findById(addSurveyRequest.getClubId());
        if(!optionalClub.isPresent()){
            throw new RestClubDoesNotExistException();
        }
        surveyQuestionRepository.deleteAllByClub_Id(optionalClub.get().getId());
        for (QuestionRequest questionRequest :addSurveyRequest.getQuestionRequests()
        ) {
            SurveyQuestion surveyQuestion = new SurveyQuestion(
                    questionRequest.getQuestion(),
                    questionRequest.isIncrease(),
                    optionalClub.get()
            );
            surveyQuestionRepository.save(surveyQuestion);
            response.add(SurveyQuestionMapper.toSurveyQuestionDTO(surveyQuestion));
        }

        return response;
    }

    @Override
    public SurveyPointDTO answerSurvey(NewSurveyAnswerRequest newSurveyAnswerRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SurveyPointDTO response= new SurveyPointDTO();
        Optional<Club> optionalClub=clubRepository.findById(newSurveyAnswerRequest.getClubId());
        if(!optionalClub.isPresent()){
            throw new RestClubDoesNotExistException();
        }
        response.setPoint(0);
        for (AnswerRequest answerRequest : newSurveyAnswerRequest.getAnswers()
        ) {
            Optional<SurveyQuestion> optionalSurveyQuestion=surveyQuestionRepository.findById(answerRequest.getQuestionId());
            if(!optionalSurveyQuestion.isPresent()){
                throw new RestSurveyQuestionDoesNotExistException();
            }
            int tempPoint = (answerRequest.getAnswer() * (optionalSurveyQuestion.get().isIncrease()?1:-1));
            response.setPoint(
                    response.getPoint()+
                            tempPoint<=0?10+tempPoint:tempPoint
                            );
        }
        response.setPoint(
                surveyQuestionRepository.countSurveyQuestionsByClub_Id(optionalClub.get().getId())
                        *10
                        /response.getPoint()
                        *100);
        SurveyPoint surveyPoint = new SurveyPoint(
                response.getPoint(),
                optionalClub.get(),
                user);
        surveyPointRepository.save(surveyPoint);

        return SurveyPointMapper.toSurveyPointDTO(surveyPoint);
    }


    @Override
    public SurveyPointDTO getSurveyPoint(Long clubId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Club> optionalClub=clubRepository.findById(clubId);
        if(!optionalClub.isPresent()){
            throw new RestClubDoesNotExistException();
        }
        Optional<SurveyPoint> optionalSurveyPoint=surveyPointRepository.getSurveyPointByClubAndUser(optionalClub.get(),user);
        if(!optionalSurveyPoint.isPresent()){
            throw new RestSurveyPointDoesNotExistException();
        }

        return SurveyPointMapper.toSurveyPointDTO(optionalSurveyPoint.get());
    }

}
