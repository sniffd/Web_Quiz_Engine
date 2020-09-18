package engine;

import java.util.ArrayList;
import java.util.List;
import model.QuizDTO;
import model.QuizResponse;
import model.SolveResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebQuizEngineController {

  List<QuizDTO> quizList = new ArrayList<>();
  List<QuizResponse> quizResponseList = new ArrayList<>();

  @PostMapping("/api/quizzes")
  public QuizDTO createQuiz(@RequestBody QuizDTO quiz) {
    QuizResponse quizResponse = new QuizResponse();

    quizResponse.setOptions(quiz.getOptions());
    quizResponse.setText(quiz.getText());
    quizResponse.setTitle(quiz.getTitle());
    quizResponse.setId(quizList.size());
    quizResponseList.add(quizResponse);
    quiz.setId(quizResponse.getId());
    quizList.add(quiz);

    return quiz;
  }

  @GetMapping("/api/quizzes/{id}")
  public ResponseEntity<QuizResponse> getQuiz(@PathVariable int id) {
    if (id >= quizResponseList.size() || quizResponseList.get(id) == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(quizResponseList.get(id), HttpStatus.OK);
  }

  @GetMapping("/api/quizzes")
  public ResponseEntity<List<QuizResponse>> getAllQuizzes() {
    return new ResponseEntity<>(quizResponseList, HttpStatus.OK);
  }

  @PostMapping("/api/quizzes/{id}/solve")
  public ResponseEntity<SolveResponse> solveQuiz(@PathVariable int id, int answer) {
    SolveResponse solveResponse = new SolveResponse();

    if (id >= quizList.size() || quizList.get(id) == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    if (answer == quizList.get(id).getAnswer()) {
      solveResponse.setSuccess(true);
      solveResponse.setFeedback("Congratulations, you're right!");
    } else {
      solveResponse.setSuccess(false);
      solveResponse.setFeedback("Wrong answer! Please, try again.");
    }
    return new ResponseEntity<>(solveResponse, HttpStatus.OK);
  }
}
