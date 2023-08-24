package project.personal.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.personal.domain.BaseEntity;
import project.personal.domain.todo.Todo;
import project.personal.domain.todo.TodoStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Todo> todos = new ArrayList<>();

    @Builder
    private User(String nickname, String email, String phoneNumber) {
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static User create(String nickname, String email, String phoneNumber) {
        return User.builder()
                .nickname(nickname)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }

}
