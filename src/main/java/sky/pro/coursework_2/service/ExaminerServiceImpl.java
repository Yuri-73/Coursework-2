package sky.pro.coursework_2.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sky.pro.coursework_2.exeptions.NotEnoughQuestionsException;
import sky.pro.coursework_2.model.Question;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final Random random = new Random();

    private final QuestionService javaService;
    private final QuestionService mathService;

    public ExaminerServiceImpl(@Qualifier("java") QuestionService javaService,
                               @Qualifier("math") QuestionService mathService) {
        this.javaService = javaService;
        this.mathService = mathService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        var allMathQuestions = mathService.getAll();
        var allJavaQuestions = javaService.getAll();
        if (amount > allJavaQuestions.size() + allMathQuestions.size()) {
            throw new NotEnoughQuestionsException(); //Объектов недостаточно
        }

        if (amount == (allJavaQuestions.size() + allMathQuestions.size())) {
            List<Question> result = new ArrayList<>(allJavaQuestions.size() + allMathQuestions.size());
            result.addAll(allMathQuestions);
            result.addAll(allJavaQuestions);
            return result;
        }

        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            result.add(random.nextBoolean() ? javaService.getRandomQuestion() : mathService.getRandomQuestion()); //Двойной рандом
        }
        return result;
    }
}
