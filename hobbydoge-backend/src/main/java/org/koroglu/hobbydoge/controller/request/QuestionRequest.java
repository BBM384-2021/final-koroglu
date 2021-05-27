package org.koroglu.hobbydoge.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class QuestionRequest {

    @NotNull(message = "Question cannot be empty.")
    private String question;

    @NotNull(message = "IsIncrease cannot be empty.")
    private boolean isIncrease;

}
