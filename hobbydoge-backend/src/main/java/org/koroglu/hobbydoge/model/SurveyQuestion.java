package org.koroglu.hobbydoge.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "surveyQuestions")
public class SurveyQuestion {

    @SequenceGenerator(
            name = "surveyQuestion_sequence",
            sequenceName = "surveyQuestion_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "surveyQuestion_sequence"
    )
    @Id
    private Long id;

    private String question;
    private boolean isIncrease;

    @ManyToOne
    private Club club;

    public SurveyQuestion(String question, boolean isIncrease, Club club) {
        this.question = question;
        this.isIncrease = isIncrease;
        this.club = club;

    }

}
