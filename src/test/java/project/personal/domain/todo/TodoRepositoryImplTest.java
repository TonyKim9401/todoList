package project.personal.domain.todo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.personal.api.service.todo.request.TodoFindServiceRequest;
import project.personal.api.service.todo.response.TodoResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class TodoRepositoryImplTest {

    @Autowired
    private TodoRepository todoRepository;

    @DisplayName("여러 조건에 따라 todo를 조회한다.")
    @Test
    void findTodosByCondition() {
        // given
        Todo 유스콘 = createTodo("유스콘", "신나는 유스콘 2023!", TodoStatus.CREATE);
        Todo 토비님 = createTodo("토비님 강의 보기", "손가락의 안녕을 기도하자", TodoStatus.CREATE);
        Todo 영한님 = createTodo("영한님 강의 보기", "JPA 로드맵 재탕 가즈아", TodoStatus.CREATE);

        todoRepository.saveAll(List.of(유스콘, 토비님, 영한님));

        // when
        TodoFindServiceRequest request = new TodoFindServiceRequest();
        List<TodoResponse> resultList = todoRepository.findByCondition(request);

        // then
        assertThat(resultList).hasSize(1);
    }

    private Todo createTodo(String todoTitle, String todoContent, TodoStatus todoStatus) {
        return Todo.builder()
                .todoTitle(todoTitle)
                .todoContent(todoContent)
                .todoStatus(todoStatus)
                .build();
    }
}