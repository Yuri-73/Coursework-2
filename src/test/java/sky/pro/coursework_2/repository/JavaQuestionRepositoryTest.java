package sky.pro.coursework_2.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sky.pro.coursework_2.model.Question;

import java.util.Set;

import static java.util.Collections.emptySet;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JavaQuestionRepositoryTest {

    JavaQuestionRepository repository;

    @BeforeEach
    public void init() {
        repository = new JavaQuestionRepository();
    }

    @Test
    void shouldAdd_WhenCorrectQuestion_ThenQuestion() {
        var result = repository.add("test", "testtest");
        assertEquals("test", result.getQuestion());
        assertEquals("testtest", result.getAnswer());
    }

    @Test
    void shouldRemove_WhenCorrectQuestion_ThenQuestion() {
        assertNull(repository.remove(new Question("test", "testtest"))); //Убеждаемся, что удалять пока нечего
        var result = repository.add("test", "testtest");
        Question removeQuestion = repository.remove(new Question("test", "testtest"));
        assertEquals(result, removeQuestion); //Убеждаемся, что удалили этот объект
    }

    @Test
    void shouldGetAll_ThenSet() {
        var result = repository.add("test", "testtest");
        assertThat(repository.getAll().iterator().next()).isEqualTo(
                new Question("test", "testtest"));  //Убеждаемся, что Сет содержит только этот объект

        var result2 = repository.add("test44", "testtest44");
        assertThat(repository.getAll().contains(result2));  //Убеждаемся, что Сет содержит этот объект
        assertThat(repository.getAll().contains(result));  //Убеждаемся, что Сет содержит и этот объект

        assertNull(repository.remove(new Question("java_question_2", "java_answer_2"))); //Убеждаемся, что такого объекта нет и объекта удаления, значит, тоже (**?**)
    }

    @Test
    void shouldGetAll_EmptySet() {
        Set<Question> set = emptySet();
        assertEquals(repository.getAll(), set);  //Убеждаемся, что Сет пустой (но почему? Он же должен содержать @PostConstructor!) (**?**)
    }
}

