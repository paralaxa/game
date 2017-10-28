package sk.stopangin.repository;

import sk.stopangin.field.Question;

import java.util.List;

public interface QuestionsRepository {
    List<Question> getAllQuestions();
}
