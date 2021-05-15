package org.koroglu.hobbydoge.repository;

import org.koroglu.hobbydoge.model.SurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, Long> {


  List<SurveyQuestion> findSurveyQuestionsByClub_Id(Long clubId);

}
