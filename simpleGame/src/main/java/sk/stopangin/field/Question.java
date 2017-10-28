package sk.stopangin.field;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Question {
    private Long id;
    private String question;
    private String answer;
    private int score;
}
