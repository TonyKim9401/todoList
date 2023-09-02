package project.personal.api.service.todo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.personal.domain.todo.Todo;
import project.personal.domain.todo.TodoStatus;
import project.personal.domain.user.User;

@Getter
@NoArgsConstructor
public class TodoUpdateServiceRequest {

    private Long todoId;
    private String todoTitle;
    private String todoContent;
    private TodoStatus todoStatus;
    private User user;

    @Builder
    private TodoUpdateServiceRequest(Long todoId, String todoTitle, String todoContent, TodoStatus todoStatus, User user) {
        this.todoId = todoId;
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.todoStatus = todoStatus;
        this.user = user;
    }

    public Todo toTodoEntity() {
        return Todo.builder()
                .todoTitle(todoTitle)
                .todoContent(todoContent)
                .build();
    }
}
