package project.personal.domain.todo;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import project.personal.domain.task.Task;
import project.personal.domain.task.TaskRepository;
import project.personal.domain.task.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@ActiveProfiles("test")
@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @DisplayName("해당 날짜 안에 만들어진 todo를 가져온다")
    @Test
    void readTodoBetweenSpecificDate() {
        // given

        Task 유스콘 = createTodo("유스콘", "신나는 유스콘 2023!", TaskStatus.CREATE);
        Task 토비님 = createTodo("토비님 강의 보기", "손가락의 안녕을 기도하자", TaskStatus.CREATE);
        Task 영한님 = createTodo("영한님 강의 보기", "JPA 로드맵 재탕 가즈아", TaskStatus.CREATE);

        taskRepository.saveAll(List.of(유스콘, 토비님, 영한님));

        LocalDateTime 종료날짜 = LocalDateTime.now();
        LocalDate 시작날짜 = LocalDateTime.now().minusDays(1).toLocalDate();

        // when
        List<Task> taskList = taskRepository.findTaskBetweenStartTimeAndEndTime(시작날짜.atStartOfDay(), 종료날짜);
        log.info("시작날짜 : {} , 종료날짜 : {}", 시작날짜, 종료날짜);
        taskList.stream().forEach(
                task -> log.info("task time : {} /// {}" ,task.getCreatedDate())

        );
        log.info("taskList size : " + taskList.size());
        // then
        assertThat(taskList).hasSize(3);
    }

    private Task createTodo(String taskTitle, String taskContent, TaskStatus taskStatus) {
        return Task.builder()
                .taskTitle(taskTitle)
                .taskContent(taskContent)
                .taskStatus(taskStatus)
                .build();
    }

}