package project.personal.api.service.todo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.personal.domain.task.Task;
import project.personal.domain.task.TaskStatus;
import project.personal.domain.user.User;

@Getter
@NoArgsConstructor
public class TaskUpdateServiceRequest {

    private Long taskId;
    private String taskTitle;
    private String taskContent;
    private TaskStatus todoStatus;
    private User user;

    @Builder
    private TaskUpdateServiceRequest(Long taskId, String taskTitle, String taskContent, TaskStatus taskStatus, User user) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
        this.todoStatus = taskStatus;
        this.user = user;
    }

    public Task toTaskEntity() {
        return Task.builder()
                .taskTitle(taskTitle)
                .taskContent(taskContent)
                .build();
    }
}
