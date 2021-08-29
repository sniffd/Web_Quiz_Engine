package engine.entity;

import engine.model.CredentialsDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(CredentialsDto credentialsDto, PasswordEncoder encoder) {
        this.email = credentialsDto.getEmail();
        this.password = encoder.encode(credentialsDto.getPassword());
    }

    public User() {

    }
}
