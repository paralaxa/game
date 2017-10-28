package sk.stopangin.field;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Question {
    private Long id;
    private String content;
    private String answer;
    private int score;
}
