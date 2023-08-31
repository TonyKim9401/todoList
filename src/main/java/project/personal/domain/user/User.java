package project.personal.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.personal.domain.BaseEntity;
import project.personal.domain.history.todo.TodoHistory;
import project.personal.domain.todo.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * @NotBlack 사용이유 : notnull = false보다 빠르게 SQL을 보내기 전에 예외를 터뜨리므로 안전하다.
 */
@Getter
@Entity(name = "users")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "nick_name")
    @NotBlank(message = "닉네임은 필수 입니다.")
    private String nickName;

    @Column(name = "email")
    @NotBlank(message = "이메일은 필수 입니다.")
    private String email;

    @Column(name = "phone_number")
    @NotBlank(message = "휴대폰 번호는 필수 입니다.")
    private String phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Todo> todos = new ArrayList<>();

    @Builder
    private User(String nickName, String email, String phoneNumber) {
        this.nickName = nickName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static User create(String nickName, String email, String phoneNumber) {
        return User.builder()
                .nickName(nickName)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }

}
