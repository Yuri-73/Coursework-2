package sky.pro.coursework_2.service;

import org.junit.jupiter.api.Test;
import sky.pro.coursework_2.exeptions.NoMathQuestionsFound;
import sky.pro.coursework_2.model.Question;
import sky.pro.coursework_2.repository.MathQuestionRepository;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MathQuestionServiceTest {

    @Test
    void sholdeGetRandomMathQuestion_WhenExistsCollection() {
        var repositoryMock = mock(MathQuestionRepository.class);
        MathQuestionService service = new MathQuestionService(repositoryMock);

        var questions = List.of(
                new Question("q1", "a1"),
                new Question("q2", "a2"),
                new Question("q3", "a3"));
        when(repositoryMock.getAll()).thenReturn(questions);

        var question = service.getRandomQuestion();
        assertTrue(questions.contains(question));
    }

    @Test
    void sholdeGetRandomMathQuestion_WhenNotCollection() {
        var repositoryMock = mock(MathQuestionRepository.class);
        MathQuestionService service = new MathQuestionService(repositoryMock);
        when(repositoryMock.getAll()).thenReturn(emptyList());

        assertThrows(NoMathQuestionsFound.class, () -> service.getRandomQuestion());
    }
}
