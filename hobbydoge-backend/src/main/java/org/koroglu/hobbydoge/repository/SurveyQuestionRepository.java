package org.koroglu.hobbydoge.repository;

import org.koroglu.hobbydoge.model.SurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, Long> {


  List<SurveyQuestion> findSurveyQuestionsByClub_Id(Long clubId);
  void deleteAllByClub_Id(Long clubId);
  int countSurveyQuestionsByClub_Id(Long clubId);

}
