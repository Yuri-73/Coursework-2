package sky.pro.coursework_2.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sky.pro.coursework_2.exeptions.NoJavaQuestionsFound;
import sky.pro.coursework_2.model.Question;
import sky.pro.coursework_2.repository.JavaQuestionRepository;
import sky.pro.coursework_2.repository.QuestionRepository;

import java.util.Collection;
import java.util.Random;

@Service("java")
public class JavaQuestionService implements QuestionService {

    private final Random random = new Random();
    private final QuestionRepository repository;

    public JavaQuestionService(@Qualifier("javaRepository") JavaQuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question add(String question, String answer) {
        return repository.add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        return repository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return repository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return repository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        var questions = repository.getAll();
        Integer randomIndex;
        if (!questions.isEmpty()) {   //NB, иначе будет стандарт-ошибка
            randomIndex = random.nextInt(questions.size());
            int index = 0;
            for (Question question : questions) {
                if (index == randomIndex) {  //Ищем совпадение рандома по индексу
                    return question;
                }
                index++;
            }
        }
        throw new NoJavaQuestionsFound();  //Билеты отсутствуют
    }
}


