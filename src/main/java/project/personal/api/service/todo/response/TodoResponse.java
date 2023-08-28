package project.personal.api.service.todo.response;

import lombok.Builder;
import lombok.Getter;
import project.personal.domain.todo.Todo;
import project.personal.domain.todo.TodoStatus;
import project.personal.domain.user.User;

import java.time.LocalDateTime;

@Getter
public class TodoResponse {

    private Long id;

    private String todoTitle;
    private String todoContent;
    private TodoStatus todoStatus;

    private User user;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Builder
    private TodoResponse(Long id, String todoTitle, String todoContent, TodoStatus todoStatus, User user, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.todoStatus = todoStatus;
        this.user = user;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public static TodoResponse of(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .todoTitle(todo.getTodoTitle())
                .todoContent(todo.getTodoContent())
                .todoStatus(todo.getTodoStatus())
                .user(todo.getUser())
                .createTime(todo.getCreatedDateTime())
                .updateTime(todo.getModifiedDateTime())
                .build();
    }
}
