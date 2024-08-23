package sky.pro.coursework_2.service;

import org.junit.jupiter.api.Test;
import sky.pro.coursework_2.exeptions.NoJavaQuestionsFound;
import sky.pro.coursework_2.model.Question;
import sky.pro.coursework_2.repository.JavaQuestionRepository;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JavaQuestionServiceTest {

    @Test
    void sholdeGetRandomJavaQuestion_WhenExistsCollection() {
        var repositoryMock = mock(JavaQuestionRepository.class);
        JavaQuestionService service = new JavaQuestionService(repositoryMock);

        var questions = List.of(
                new Question("q1", "a1"),
                new Question("q2", "a2"),
                new Question("q3", "a3"));
        when(repositoryMock.getAll()).thenReturn(questions);

        var question = service.getRandomQuestion();
        assertTrue(questions.contains(question));
    }

    @Test
    void sholdeGetRandomJavaQuestion_WhenNotCollection() {
        var repositoryMock = mock(JavaQuestionRepository.class);
        JavaQuestionService service = new JavaQuestionService(repositoryMock);
        when(repositoryMock.getAll()).thenReturn(emptyList());

        assertThrows(NoJavaQuestionsFound.class, () -> service.getRandomQuestion());
    }

}