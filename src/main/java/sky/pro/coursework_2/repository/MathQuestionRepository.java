package sky.pro.coursework_2.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sky.pro.coursework_2.model.Question;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository("mathRepository")
public class MathQuestionRepository implements QuestionRepository {
    private final Set<Question> questions = new HashSet<>();

//    @PostConstruct
//    void init() {
//        add("math_question_1", "math_answer_1");
//        add("math_question_2", "math_answer_2");
//        add("math_question_3", "math_answer_3");
//        add("math_question_4", "math_answer_4");
//        add("math_question_5", "math_answer_5");
//    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        }
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }
}

