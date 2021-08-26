package engine.service;

import engine.entity.Quiz;
import engine.model.AnswerDTO;
import engine.model.QuizDTO;
import engine.model.QuizResponse;
import engine.model.SolveResponse;
import org.springframework.stereotype.Service;
import engine.persistance.QuizRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public QuizResponse create(QuizDTO quizDTO) {
        Quiz quiz = quizRepository.save(new Quiz(quizDTO));
        return new QuizResponse(quiz);
    }

    public Quiz get(long id) {
        return quizRepository.findById(id).get();
    }

    public List<QuizResponse> getAll() {
        return quizRepository.findAll().stream()
                .map(QuizResponse::new)
                .collect(Collectors.toList());
    }

    public boolean isExist(long id) {
        return quizRepository.existsById(id);
    }

    public SolveResponse solve(int id, AnswerDTO answer) {
        SolveResponse solveResponse = new SolveResponse();
        if (answer.getAnswer().equals(get(id).getAnswer())) {
            solveResponse.setSuccess(true);
            solveResponse.setFeedback("Congratulations, you're right!");
        } else {
            solveResponse.setSuccess(false);
            solveResponse.setFeedback("Wrong answer! Please, try again.");
        }

        return solveResponse;
    }
}
