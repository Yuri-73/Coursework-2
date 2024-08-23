package sky.pro.coursework_2.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.coursework_2.exeptions.NotEnoughQuestionsException;
import sky.pro.coursework_2.model.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    JavaQuestionService javaQuestionService;
    @Mock
    MathQuestionService mathQuestionService;
    @InjectMocks
    ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
    }

    @Test
    void shoulGetQuestions_WhenAmountMore_ThenFullCollection() {
        assertThrows(NotEnoughQuestionsException.class, () -> examinerService.getQuestions(1));
    }

    @Test
    void shoulGetQuestions_WhenAmountEquals_ThenFullCollection() {
        var javaQuestions = List.of(new Question("q1", "a1"));
        var mathQuestions = List.of(new Question("q2", "a2"));
        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        when(mathQuestionService.getAll()).thenReturn(mathQuestions);

        var result = examinerService.getQuestions(2);

        Assertions.assertThat(result).containsExactlyInAnyOrder(
                new Question("q1", "a1"),
                new Question("q2", "a2"));
    }

    @Test
    void shoulGetQuestions_WhenAmountLess_ThenCollection() {
        var javaQuestions = List.of(new Question("q1", "a1"), new Question("javaq1", "javaa1"));
        var mathQuestions = List.of(new Question("q2", "a2"));

        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        when(mathQuestionService.getAll()).thenReturn(mathQuestions);

        when(javaQuestionService.getRandomQuestion()).thenReturn(
                new Question("randomq1", "randoma1"));
        when(mathQuestionService.getRandomQuestion()).thenReturn(
                new Question("randomq2", "randoma2"));

        var result = examinerService.getQuestions(2);

        Assertions.assertThat(result).containsExactlyInAnyOrder(
                new Question("randomq1", "randoma1"),
                new Question("randomq2", "randoma2"));
    }
}