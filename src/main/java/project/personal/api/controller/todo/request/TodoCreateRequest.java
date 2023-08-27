package project.personal.api.controller.todo.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.personal.api.service.todo.request.TodoCreateServiceRequest;
import project.personal.domain.user.User;

@Getter
@NoArgsConstructor
public class TodoCreateRequest {

    @NotEmpty(message = "제목은 필수 값 입니다.")
    private String todoTitle;

    private String todoContent;

    private User user;

    public TodoCreateServiceRequest toServiceRequest() {
        return TodoCreateServiceRequest.builder()
                .todoTitle(todoTitle)
                .todoContent(todoContent)
                .user(user)
                .build();
    }
}
