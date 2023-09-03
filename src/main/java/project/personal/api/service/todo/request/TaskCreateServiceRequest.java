package project.personal.api.service.todo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.personal.domain.task.Task;
import project.personal.domain.user.User;

@Getter
@NoArgsConstructor
public class TaskCreateServiceRequest {

    private String taskTitle;
    private String taskContent;
    private User user;

    @Builder
    private TaskCreateServiceRequest(String taskTitle, String taskContent, User user) {
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
        this.user = user;
    }

    public Task toTaskEntity() {
        return Task.builder()
                .taskTitle(taskTitle)
                .taskContent(taskContent)
                .build();
    }
}
