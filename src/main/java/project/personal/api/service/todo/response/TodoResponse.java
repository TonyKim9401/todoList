package project.personal.api.service.todo.response;

import lombok.Builder;
import lombok.Getter;
import project.personal.domain.todo.Todo;
import project.personal.domain.todo.TodoStatus;

import java.time.LocalDateTime;

@Getter
public class TodoResponse {

    private Long id;

    private TodoStatus todoStatus;
    private LocalDateTime registeredDateTime;

    @Builder
    private TodoResponse(Long id, TodoStatus todoStatus, LocalDateTime registeredDateTime) {
        this.id = id;
        this.todoStatus = todoStatus;
        this.registeredDateTime = registeredDateTime;
    }

    public static TodoResponse of(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .todoStatus(todo.getTodoStatus())
                .registeredDateTime(todo.getRegisteredDateTime())
                .build();
    }
}
