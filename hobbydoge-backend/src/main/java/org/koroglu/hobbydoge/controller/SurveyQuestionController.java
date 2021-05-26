package org.koroglu.hobbydoge.controller;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.AddSurveyRequest;
import org.koroglu.hobbydoge.controller.request.NewClubRequest;
import org.koroglu.hobbydoge.controller.request.NewSurveyAnswerRequest;
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
    return ResponseEntity.ok().body(surveyService.getSurvey(clubId));
  }

  @GetMapping("/surveyPoint/{clubId}")
  public ResponseEntity<?> getSurveyPoint(@PathVariable(value = "clubId", required = true) Long clubId) {
    return ResponseEntity.ok().body(surveyService.getSurveyPoint(clubId));
  }

  @PostMapping("")
  public ResponseEntity<?> createSurvey(@RequestBody @Valid AddSurveyRequest addSurveyRequest) {
    return ResponseEntity.ok().body(surveyService.createSurvey(addSurveyRequest));
  }

  @PutMapping("")
  public ResponseEntity<?> updateSurvey(@RequestBody @Valid AddSurveyRequest addSurveyRequest) {
    return ResponseEntity.ok().body(surveyService.updateSurvey(addSurveyRequest));
  }

  @PostMapping("answerSurvey")
  public ResponseEntity<?> answerSurvey(@RequestBody @Valid NewSurveyAnswerRequest newSurveyAnswerRequest) {
    return ResponseEntity.ok().body(surveyService.answerSurvey(newSurveyAnswerRequest));
  }

}
