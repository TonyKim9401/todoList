package project.personal.api.controller.todo.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.personal.api.service.todo.request.TaskUpdateServiceRequest;
import project.personal.domain.task.TaskStatus;
import project.personal.domain.user.User;

@Getter
@NoArgsConstructor
public class TaskUpdateRequest {

    private Long taskId;

    @NotEmpty(message = "제목은 필수 값 입니다.")
    private String taskTitle;

    private String taskContent;

    private TaskStatus taskStatus;

    private User user;

    public TaskUpdateServiceRequest toUpdateServiceRequest() {
        return TaskUpdateServiceRequest.builder()
                .taskId(taskId)
                .taskContent(taskContent)
                .taskStatus(TaskStatus.UPDATE)
                .build();
    }
}
