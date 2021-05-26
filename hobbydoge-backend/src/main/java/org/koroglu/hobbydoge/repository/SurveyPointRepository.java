package org.koroglu.hobbydoge.repository;

import org.koroglu.hobbydoge.model.Club;
import org.koroglu.hobbydoge.model.SurveyPoint;
import org.koroglu.hobbydoge.model.SurveyQuestion;
import org.koroglu.hobbydoge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SurveyPointRepository extends JpaRepository<SurveyPoint, Long> {


    Optional<SurveyPoint> getSurveyPointByClubAndUser(Club club, User user);

}
