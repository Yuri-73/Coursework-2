package sky.pro.coursework_2.repository;


import sky.pro.coursework_2.model.Question;

import java.util.Collection;

public interface QuestionRepository {

    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();
}
