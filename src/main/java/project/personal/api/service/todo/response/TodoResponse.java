package project.personal.api.service.todo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import project.personal.domain.todo.Todo;
import project.personal.domain.todo.TodoStatus;
import project.personal.domain.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class TodoResponse {

    private Long id;

    private String todoTitle;
    private String todoContent;
    private TodoStatus todoStatus;

    private User user;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    private TodoResponse(Long id, String todoTitle, String todoContent, TodoStatus todoStatus, User user, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.todoStatus = todoStatus;
        this.user = user;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static TodoResponse of(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .todoTitle(todo.getTodoTitle())
                .todoContent(todo.getTodoContent())
                .todoStatus(todo.getTodoStatus())
                .user(todo.getUser())
                .createdDate(todo.getCreatedDate())
                .modifiedDate(todo.getModifiedDate())
                .build();
    }
}
