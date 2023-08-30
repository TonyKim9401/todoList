package project.personal.domain.user;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@Transactional
@ActiveProfiles("test")
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAllInBatch();
    }

    @DisplayName("신규 회원 저장에 성공한다.")
    @Test
    void saveUserSuccess() {
        // given
        User user = User.builder()
                .nickName("nickName")
                .email("user@gmail.com")
                .phoneNumber("010-1234-5678")
                .build();

        // when
        User savedUser = userRepository.save(user);
        User findUser = userRepository.findById(user.getUserId()).get();

        // then
        assertThat(findUser.getNickName()).isEqualTo(user.getNickName());
        assertThat(findUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(findUser.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
    }

    @DisplayName("닉네임이 없을 경우 신규 회원 저장에 실패한다.")
    @Test
    void saveUserFailByNickName() {
        // given
        User user = User.builder()
                .email("user@gmail.com")
                .phoneNumber("010-1234-5678")
                .build();

        // when // then
        /**
         * @NotBlank(message = "닉네임은 필수 입니다.")
         */
        assertThatThrownBy(() -> userRepository.save(user))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @DisplayName("이메일이 없을 경우 신규 회원 저장에 실패한다.")
    @Test
    void saveUserFailByEmail() {
        // given
        User user = User.builder()
                .nickName("nickName")
                .phoneNumber("010-1234-5678")
                .build();


        // when // then
        assertThatThrownBy(() -> userRepository.save(user))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @DisplayName("휴대전화 번호가 없을 경우 신규 회원 저장에 실패한다.")
    @Test
    void saveUserFailByPhoneNumber() {
        // given
        User user = User.builder()
                        .nickName("nickName")
                        .email("user@gmail.com")
                        .build();

        // when // then
        assertThatThrownBy(() -> userRepository.save(user))
                .isInstanceOf(ConstraintViolationException.class);
    }



}