package engine.controller;

import engine.model.CredentialsDto;
import engine.model.QuizDTO;
import engine.model.QuizResponse;
import engine.service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/api/register")
    public ResponseEntity<Object> register(@RequestBody @Valid CredentialsDto credentialsDto) {
        registerService.register(credentialsDto);
        return ResponseEntity.ok().build();
    }
}
