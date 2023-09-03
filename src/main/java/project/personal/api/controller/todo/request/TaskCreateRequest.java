package project.personal.api.controller.todo.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.personal.api.service.todo.request.TaskCreateServiceRequest;
import project.personal.domain.user.User;

@Getter
@NoArgsConstructor
public class TaskCreateRequest {

    @NotEmpty(message = "제목은 필수 값 입니다.")
    private String taskTitle;

    private String taskContent;

    private User user;

    public TaskCreateServiceRequest toServiceRequest(User user) {
        return TaskCreateServiceRequest.builder()
                .taskTitle(taskTitle)
                .taskContent(taskContent)
                .user(user)
                .build();
    }
}
