package org.koroglu.hobbydoge.service;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.AddSurveyRequest;
import org.koroglu.hobbydoge.controller.request.QuestionRequest;
import org.koroglu.hobbydoge.dto.mapper.SurveyQuestionMapper;
import org.koroglu.hobbydoge.dto.model.SurveyQuestionDTO;
import org.koroglu.hobbydoge.model.SurveyQuestion;
import org.koroglu.hobbydoge.repository.ClubRepository;
import org.koroglu.hobbydoge.repository.SurveyQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class SurveyServiceImpl implements SurveyService {

  private final SurveyQuestionRepository surveyQuestionRepository;
  private final ClubRepository clubRepository;

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

        for (QuestionRequest questionRequest :addSurveyRequest.getQuestionRequests()
             ) {
            SurveyQuestion surveyQuestion = new SurveyQuestion(
                    questionRequest.getQuestion(),
                    questionRequest.isIncrease(),
                    clubRepository.getOne(addSurveyRequest.getClubId())
            );
            surveyQuestionRepository.save(surveyQuestion);
            response.add(SurveyQuestionMapper.toSurveyQuestionDTO(surveyQuestion));
        }


        return response;
    }

}
