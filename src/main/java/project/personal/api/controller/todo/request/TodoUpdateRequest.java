package project.personal.api.controller.todo.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.personal.api.service.todo.request.TodoCreateServiceRequest;
import project.personal.api.service.todo.request.TodoUpdateServiceRequest;
import project.personal.domain.todo.TodoStatus;
import project.personal.domain.user.User;

@Getter
@NoArgsConstructor
public class TodoUpdateRequest {

    private Long todoId;

    @NotEmpty(message = "제목은 필수 값 입니다.")
    private String todoTitle;

    private String todoContent;

    private TodoStatus todoStatus;

    private User user;

    public TodoUpdateServiceRequest toUpdateServiceRequest() {
        return TodoUpdateServiceRequest.builder()
                .todoId(todoId)
                .todoContent(todoContent)
                .todoStatus(TodoStatus.UPDATE)
                .build();
    }
}
