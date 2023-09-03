package project.personal.domain.todo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import project.personal.api.service.todo.request.TaskFindServiceRequest;
import project.personal.api.service.todo.response.TaskResponse;
import project.personal.domain.task.Task;
import project.personal.domain.task.TaskRepository;
import project.personal.domain.task.TaskStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@ActiveProfiles("test")
@SpringBootTest
class TaskRepositoryImplTest {

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        Task 유스콘 = createTodo("유스콘", "신나는 유스콘 2023 가즈아!", TaskStatus.CREATE);
        Task 토비님 = createTodo("토비님 강의 보기", "손가락의 안녕을 기도 가즈아", TaskStatus.CREATE);
        Task 영한님 = createTodo("영한님 강의 보기", "신나는 JPA 로드맵 재탕 가즈아", TaskStatus.DELETE);
        taskRepository.saveAll(List.of(유스콘, 토비님, 영한님));
    }

    @AfterEach
    void tearDown() {
//        todoRepository.deleteAllInBatch();
    }

    @DisplayName("todoStatus 상태에 따라 todo를 조회한다.")
    @Test
    void findTodosByCondition() {
        // given
        TaskFindServiceRequest create = TaskFindServiceRequest.builder().taskStatus(TaskStatus.CREATE).build();
        TaskFindServiceRequest delete = TaskFindServiceRequest.builder().taskStatus(TaskStatus.DELETE).build();
        TaskFindServiceRequest start = TaskFindServiceRequest.builder().taskStatus(TaskStatus.START).build();

        // when
        List<TaskResponse> createList = taskRepository.findByCondition(create);
        List<TaskResponse> deleteList = taskRepository.findByCondition(delete);
        List<TaskResponse> startList = taskRepository.findByCondition(start);


        // then
        assertThat(createList).hasSize(2);
        assertThat(deleteList).hasSize(1);
        assertThat(startList).hasSize(0);
    }

    /**
     * createdDate 컬럼은 updatable = false 로 되어있기 때문에 테스트를 위해서는 잠시 true로 바꾸고 해야 한다.
     * 그냥 돌리면 계속 노란불이 나오기 때문에 @Disabled를 설정해 두었다.
     */
    @DisplayName("task의 생성일이 기본 오늘기준 30일 전까지만 조회한다.")
    @Test
    @Disabled
    void findTaskCreatedDateBetweenThirtyDaysAgoAndTodayNow() {
        // given
        TaskFindServiceRequest request = TaskFindServiceRequest.builder().build();
        List<Task> findList = taskRepository.findAll();

        /*
        for (Todo t : findList) {
            if (t.getTodoTitle().equals("유스콘")) {
                t.setCreatedDate(LocalDateTime.now().minusDays(31));
            } else if (t.getTodoTitle().equals("토비님")) {
                t.setCreatedDate(LocalDateTime.now().minusDays(30));
            } else {
                t.setCreatedDate(LocalDateTime.now().minusDays(29));
            }
        }
        */

        // when
        List<TaskResponse> createdDateList = taskRepository.findByCondition(request);

        createdDateList.stream().forEach(
                task -> {
                    log.info("title = {} , createdate = {}" , task.getTaskTitle(), task.getCreatedDate());
                }
        );

        // then
        assertThat(createdDateList).hasSize(2);

    }

    @DisplayName("taskTitle 제목으로 task를 조회한다.")
    @Test
    void findTodoByTodoTitleWithLike() {
        // when
        TaskFindServiceRequest 유 = TaskFindServiceRequest.builder().taskTitle("유").build();
        TaskFindServiceRequest 강의 = TaskFindServiceRequest.builder().taskTitle("강의").build();
        TaskFindServiceRequest 유스콘 = TaskFindServiceRequest.builder().taskTitle("유스콘").build();
        TaskFindServiceRequest 영한님 = TaskFindServiceRequest.builder().taskTitle("영한님").build();

        // then
        List<TaskResponse> 유list = taskRepository.findByCondition(유);
        List<TaskResponse> 유스콘list = taskRepository.findByCondition(유스콘);
        List<TaskResponse> 영한님list = taskRepository.findByCondition(영한님);
        List<TaskResponse> 강의list = taskRepository.findByCondition(강의);

        assertThat(유list).hasSize(1);
        assertThat(유스콘list).hasSize(1);
        assertThat(영한님list).hasSize(1);
        assertThat(강의list).hasSize(2);
    }

    @DisplayName("taskContent 내용으로 task를 조회한다.")
    @Test
    void findTodoByTodoContentWithLike() {
        // when
        TaskFindServiceRequest 기도 = TaskFindServiceRequest.builder().taskContent("기도").build();
        TaskFindServiceRequest 신나는 = TaskFindServiceRequest.builder().taskContent("신나는").build();
        TaskFindServiceRequest 가즈아 = TaskFindServiceRequest.builder().taskContent("가즈아").build();

        // then
        List<TaskResponse> 기도list = taskRepository.findByCondition(기도);
        List<TaskResponse> 신나는list = taskRepository.findByCondition(신나는);
        List<TaskResponse> 가즈아list = taskRepository.findByCondition(가즈아);

        assertThat(기도list).hasSize(1);
        assertThat(신나는list).hasSize(2);
        assertThat(가즈아list).hasSize(3);
    }


    private Task createTodo(String taskTitle, String taskContent, TaskStatus taskStatus) {
        return Task.builder()
                .taskTitle(taskTitle)
                .taskContent(taskContent)
                .taskStatus(taskStatus)
                .build();
    }
}