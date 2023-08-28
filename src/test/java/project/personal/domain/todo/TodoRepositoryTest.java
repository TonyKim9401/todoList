package project.personal.domain.todo;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@ActiveProfiles("test")
@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @DisplayName("해당 날짜 안에 만들어진 todo를 가져온다")
    @Test
    void readTodoBetweenSpecificDate() {
        // given

        Todo 유스콘 = createTodo("유스콘", "신나는 유스콘 2023!", TodoStatus.CREATE);
        Todo 토비님 = createTodo("토비님 강의 보기", "손가락의 안녕을 기도하자", TodoStatus.CREATE);
        Todo 영한님 = createTodo("영한님 강의 보기", "JPA 로드맵 재탕 가즈아", TodoStatus.CREATE);

        todoRepository.saveAll(List.of(유스콘, 토비님, 영한님));

        LocalDateTime 종료날짜 = LocalDateTime.now();
        LocalDate 시작날짜 = LocalDateTime.now().minusDays(1).toLocalDate();

        // when
        List<Todo> todoList = todoRepository.findTodoBetweenStartTimeAndEndTime(시작날짜.atStartOfDay(), 종료날짜);
        log.info("시작날짜 : {} , 종료날짜 : {}", 시작날짜, 종료날짜);
        todoList.stream().forEach(
                todo -> log.info("todo time : {} /// {}" ,todo.getCreatedDate())

        );
        log.info("todoList size : " + todoList.size());
        // then
        assertThat(todoList).hasSize(3);
    }

    private Todo createTodo(String todoTitle, String todoContent, TodoStatus todoStatus) {
        return Todo.builder()
                .todoTitle(todoTitle)
                .todoContent(todoContent)
                .todoStatus(todoStatus)
                .build();
    }

}