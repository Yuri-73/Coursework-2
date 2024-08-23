package sky.pro.coursework_2.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sky.pro.coursework_2.model.Question;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository("javaRepository")
public class JavaQuestionRepository implements QuestionRepository {

    private final Set<Question> questions = new HashSet<>();

//    @PostConstruct
//    void init() {
//        add("java_question_2", "java_answer_2");
//        add("java_question_3", "java_answer_3");
//        add("java_question_1", "java_answer_1");
//        add("java_question_4", "java_answer_4");
//        add("java_question_5", "java_answer_5");
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
