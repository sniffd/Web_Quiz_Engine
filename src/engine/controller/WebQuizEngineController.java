package engine.controller;

import engine.model.AnswerDTO;
import engine.model.QuizDTO;
import engine.model.QuizResponse;
import engine.model.SolveResponse;
import engine.service.MyUserPrincipal;
import engine.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class WebQuizEngineController {

    private final QuizService quizService;

    public WebQuizEngineController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/api/quizzes")
    public ResponseEntity<QuizResponse> createQuiz(@RequestBody @Valid QuizDTO quiz, Principal principal) {
        return ResponseEntity.ok(quizService.create(quiz, principal.getName()));
    }

    @GetMapping("/api/quizzes/{id}")
    public ResponseEntity<QuizResponse> getQuiz(@PathVariable long id) {
        if (!quizService.isExist(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new QuizResponse(quizService.get(id)));
    }

    @GetMapping("/api/quizzes")
    public ResponseEntity<List<QuizResponse>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAll());
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public ResponseEntity<SolveResponse> solveQuiz(@PathVariable long id, @RequestBody AnswerDTO answer) {
        if (!quizService.isExist(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(quizService.solve(id, answer));
    }

    @DeleteMapping("/api/quizzes/{id}")
    public ResponseEntity<QuizResponse> deleteQuiz(@PathVariable long id, Principal principal) {
        if (!quizService.isExist(id)) {
            return ResponseEntity.notFound().build();
        }
        if (principal.getName().equals(quizService.get(id).getAuthor())) {
            quizService.delete(id);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.noContent().build();
    }
}
