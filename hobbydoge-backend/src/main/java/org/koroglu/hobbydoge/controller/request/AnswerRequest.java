package org.koroglu.hobbydoge.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class AnswerRequest {


    @NotNull(message = "QuestionId cannot be empty.")
    private Long questionId;

    @Min(value = 0,message = "Answer should not be less than 0.")
    @Max(value = 10,message = "Answer should not be more than 10 ")
    private Integer answer;

}
