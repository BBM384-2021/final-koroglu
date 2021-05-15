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

    @Size(min = 15, message = "Description should not be less than 15 characters.")
    @NotNull(message = "Name cannot be empty.")
    private String question;

    @NotNull(message = "Description cannot be empty.")
    private boolean isIncrease;

}
