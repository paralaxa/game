package sk.stopangin.repository;

import org.springframework.stereotype.Component;
import sk.stopangin.field.Question;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryQuestionsRepositoryImpl implements QuestionsRepository {
    private List<Question> questions = new ArrayList<>();

    @PostConstruct
    private void init() {
        Question q1 = Question.builder()
                .id(1l)
                .content("kolko dni ma januar")
                .score(1)
                .answer("31")
                .build();

        Question q2 = Question.builder()
                .id(2l)
                .content("kolko dni ma marec")
                .score(2)
                .answer("31")
                .build();

        Question q3 = Question.builder()
                .id(3l)
                .content("kolko dni ma april")
                .score(3)
                .answer("30")
                .build();

        Question q4 = Question.builder()
                .id(4l)
                .content("kolko dni ma maj")
                .score(4)
                .answer("31")
                .build();

        Question q5 = Question.builder()
                .id(5l)
                .content("kolko dni ma jun")
                .score(5)
                .answer("30")
                .build();

        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
        questions.add(q5);
    }

    public List<Question> getAllQuestions() {
        init();
        return questions;
    }
}
