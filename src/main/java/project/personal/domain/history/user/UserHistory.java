package project.personal.domain.history.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.personal.domain.BaseEntity;
import project.personal.domain.user.User;

@Getter
@Entity(name = "users_history")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class UserHistory extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userHistoryId;
    private String nickname;
    private String email;
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    @Builder
    private UserHistory(String nickname, String email, String phoneNumber) {
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static UserHistory create(String nickname, String email, String phoneNumber) {
        return UserHistory.builder()
                .nickname(nickname)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }

}
