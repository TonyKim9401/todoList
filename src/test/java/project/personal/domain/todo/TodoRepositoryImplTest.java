package project.personal.domain.todo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import project.personal.api.service.todo.request.TodoFindServiceRequest;
import project.personal.api.service.todo.response.TodoResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@ActiveProfiles("test")
@SpringBootTest
class TodoRepositoryImplTest {

    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        Todo 유스콘 = createTodo("유스콘", "신나는 유스콘 2023 가즈아!", TodoStatus.CREATE);
        Todo 토비님 = createTodo("토비님 강의 보기", "손가락의 안녕을 기도 가즈아", TodoStatus.CREATE);
        Todo 영한님 = createTodo("영한님 강의 보기", "신나는 JPA 로드맵 재탕 가즈아", TodoStatus.DELETE);
        todoRepository.saveAll(List.of(유스콘, 토비님, 영한님));
    }

    @AfterEach
    void tearDown() {
//        todoRepository.deleteAllInBatch();
    }

    @DisplayName("todoStatus 상태에 따라 todo를 조회한다.")
    @Test
    void findTodosByCondition() {
        // given
        TodoFindServiceRequest create = TodoFindServiceRequest.builder().todoStatus(TodoStatus.CREATE).build();
        TodoFindServiceRequest delete = TodoFindServiceRequest.builder().todoStatus(TodoStatus.DELETE).build();
        TodoFindServiceRequest start = TodoFindServiceRequest.builder().todoStatus(TodoStatus.START).build();

        // when
        List<TodoResponse> createList = todoRepository.findByCondition(create);
        List<TodoResponse> deleteList = todoRepository.findByCondition(delete);
        List<TodoResponse> startList = todoRepository.findByCondition(start);


        // then
        assertThat(createList).hasSize(2);
        assertThat(deleteList).hasSize(1);
        assertThat(startList).hasSize(0);
    }

    /**
     * createdDate 컬럼은 updatable = false 로 되어있기 때문에 테스트를 위해서는 잠시 true로 바꾸고 해야 한다.
     * 그냥 돌리면 계속 노란불이 나오기 때문에 @Disabled를 설정해 두었다.
     */
    @DisplayName("todo의 생성일이 기본 오늘기준 30일 전까지만 조회한다.")
    @Test
    @Disabled
    void findTodoCreatedDateBetweenThirtyDaysAgoAndTodayNow() {
        // given
        TodoFindServiceRequest request = TodoFindServiceRequest.builder().build();
        List<Todo> findList = todoRepository.findAll();

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
        List<TodoResponse> createdDateList = todoRepository.findByCondition(request);

        createdDateList.stream().forEach(
                todo -> {
                    log.info("title = {} , createdate = {}" , todo.getTodoTitle(), todo.getCreatedDate());
                }
        );

        // then
        assertThat(createdDateList).hasSize(2);

    }

    @DisplayName("todoTitle 제목으로 todo를 조회한다.")
    @Test
    void findTodoByTodoTitleWithLike() {
        // when
        TodoFindServiceRequest 유 = TodoFindServiceRequest.builder().todoTitle("유").build();
        TodoFindServiceRequest 강의 = TodoFindServiceRequest.builder().todoTitle("강의").build();
        TodoFindServiceRequest 유스콘 = TodoFindServiceRequest.builder().todoTitle("유스콘").build();
        TodoFindServiceRequest 영한님 = TodoFindServiceRequest.builder().todoTitle("영한님").build();

        // then
        List<TodoResponse> 유list = todoRepository.findByCondition(유);
        List<TodoResponse> 유스콘list = todoRepository.findByCondition(유스콘);
        List<TodoResponse> 영한님list = todoRepository.findByCondition(영한님);
        List<TodoResponse> 강의list = todoRepository.findByCondition(강의);

        assertThat(유list).hasSize(1);
        assertThat(유스콘list).hasSize(1);
        assertThat(영한님list).hasSize(1);
        assertThat(강의list).hasSize(2);
    }

    @DisplayName("todoContent 내용으로 todo를 조회한다.")
    @Test
    void findTodoByTodoContentWithLike() {
        // when
        TodoFindServiceRequest 기도 = TodoFindServiceRequest.builder(). todoContent("기도").build();
        TodoFindServiceRequest 신나는 = TodoFindServiceRequest.builder().todoContent("신나는").build();
        TodoFindServiceRequest 가즈아 = TodoFindServiceRequest.builder().todoContent("가즈아").build();

        // then
        List<TodoResponse> 기도list = todoRepository.findByCondition(기도);
        List<TodoResponse> 신나는list = todoRepository.findByCondition(신나는);
        List<TodoResponse> 가즈아list = todoRepository.findByCondition(가즈아);

        assertThat(기도list).hasSize(1);
        assertThat(신나는list).hasSize(2);
        assertThat(가즈아list).hasSize(3);
    }


    private Todo createTodo(String todoTitle, String todoContent, TodoStatus todoStatus) {
        return Todo.builder()
                .todoTitle(todoTitle)
                .todoContent(todoContent)
                .todoStatus(todoStatus)
                .build();
    }
}