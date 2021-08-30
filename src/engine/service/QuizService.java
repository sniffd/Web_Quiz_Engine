package engine.service;

import engine.entity.Completion;
import engine.entity.Quiz;
import engine.model.*;
import engine.persistance.CompletionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import engine.persistance.QuizRepository;

import java.sql.Date;
import java.time.Instant;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final CompletionRepository completionRepository;

    public QuizService(QuizRepository quizRepository, CompletionRepository completionRepository) {
        this.quizRepository = quizRepository;
        this.completionRepository = completionRepository;
    }

    public QuizResponse create(QuizDTO quizDTO, String author) {
        Quiz quiz = quizRepository.save(new Quiz(quizDTO, author));
        return new QuizResponse(quiz);
    }

    public Quiz get(long id) {
        return quizRepository.findById(id).get();
    }

    public Page<QuizResponse> getAll(int page) {
        Pageable paging = PageRequest.of(page, 10);
        return quizRepository.findAll(paging).map(QuizResponse::new);
    }

    public boolean isExist(long id) {
        return quizRepository.existsById(id);
    }

    public SolveResponse solve(long id, AnswerDTO answer, String user) {
        SolveResponse solveResponse = new SolveResponse();
        if (answer.getAnswer().equals(get(id).getAnswer())) {
            solveResponse.setSuccess(true);
            solveResponse.setFeedback("Congratulations, you're right!");
            completionRepository.save(new Completion(id, Date.from(Instant.now()), user));
        } else {
            solveResponse.setSuccess(false);
            solveResponse.setFeedback("Wrong answer! Please, try again.");
        }

        return solveResponse;
    }

    public Page<CompletionDto> getCompleted(int page, String user) {
        Pageable paging = PageRequest.of(page, 10, Sort.by("completedAt").descending());
        return completionRepository.findByUser(user, paging).map(CompletionDto::new);
    }

    public void delete(long id) {
        quizRepository.deleteById(id);
    }
}
