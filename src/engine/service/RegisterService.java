package engine.service;

import engine.entity.User;
import engine.model.CredentialsDto;
import engine.persistance.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

    public void register(CredentialsDto credentialsDto) {
        userRepository.save(new User(credentialsDto, encoder));
    }

    public RegisterService(BCryptPasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }
}
