package sky.pro.coursework_2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.coursework_2.model.Question;
import sky.pro.coursework_2.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/get")
public class ExamController {

    private final ExaminerService service;

    public ExamController(ExaminerService service) {
        this.service = service;
    }

    @GetMapping("/{amount}")
    public Collection<Question> getQuestions(@PathVariable int amount) {
        return service.getQuestions(amount);

    }
}
