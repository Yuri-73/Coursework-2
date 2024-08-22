package sky.pro.coursework_2.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(NoJavaQuestionsFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoJavaQuestionsFound(NoJavaQuestionsFound e) {
        System.out.println("Билеты Java отсутствуют!");
    }

    @ExceptionHandler(NoMathQuestionsFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoMathQuestionsFound(NoMathQuestionsFound e) {
        System.out.println("Билеты Math отсутствуют!");
    }

    @ExceptionHandler(NotEnoughQuestionsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleFieldValueException(NotEnoughQuestionsException e) {
        System.out.println("Недостаточно общее количество билетов!");
    }
}
