package engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebQuizEngine {

    public static void main(String[] args) {
        SpringApplication.run(WebQuizEngine.class, args);
    }

    @GetMapping(path = "/api/quiz")
    public Quiz getQuiz() {
        return new Quiz("The Java Logo", "What is depicted on the Java logo?", new String[]{"Robot", "Tea leaf", "Cup of coffee", "Bug"});
    }

    @PostMapping(path = "/api/quiz")
    public Solution solveQuiz(@RequestParam int answer) {
        if (answer == 2)
            return new Solution(true, "Congratulations, you're right!");
        else
            return new Solution(false, "Wrong answer! Please, try again.");
    }
}
