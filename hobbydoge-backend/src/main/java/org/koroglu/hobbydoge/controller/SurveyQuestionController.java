package org.koroglu.hobbydoge.controller;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.AddSurveyRequest;
import org.koroglu.hobbydoge.controller.request.NewClubRequest;
import org.koroglu.hobbydoge.service.SurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/surveyQuestion")
@AllArgsConstructor
public class SurveyQuestionController {

  private final SurveyService surveyService;

  @GetMapping("/{clubId}")
  public ResponseEntity<?> getSurvey(@PathVariable(value = "clubId", required = true) Long clubId) {
    System.out.println(String.format("ClubId: %d", clubId ));
    return ResponseEntity.ok().body(surveyService.getSurvey(clubId));
  }

  @PostMapping("")
  public ResponseEntity<?> createSurvey(@RequestBody @Valid AddSurveyRequest addSurveyRequest) {
    //System.out.println(String.format("ClubId: %d", clubId ));
    return ResponseEntity.ok().body(surveyService.createSurvey(addSurveyRequest));
  }

}
