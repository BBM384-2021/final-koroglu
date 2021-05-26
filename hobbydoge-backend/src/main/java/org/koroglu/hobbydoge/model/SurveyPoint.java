package org.koroglu.hobbydoge.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "surveyPoints")
public class SurveyPoint {

    @SequenceGenerator(
            name = "surveyPoint_sequence",
            sequenceName = "surveyPoint_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "surveyPoint_sequence"
    )

    @Id
    private Long id;
    private Integer point;
    private Date date;

    @ManyToOne
    private Club club;

    @ManyToOne
    private User user;

    public SurveyPoint(int point, Club club, User user) {
        this.point = point;
        this.user = user;
        this.club = club;
        this.date = new Date();
    }
}
