package project.personal.api.service.todo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.personal.domain.task.Task;
import project.personal.domain.task.TaskStatus;
import project.personal.domain.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class TaskResponse {

    private Long taskId;

    private String taskTitle;
    private String taskContent;
    private TaskStatus taskStatus;

    private User user;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    private TaskResponse(Long taskId, String taskTitle, String taskContent, TaskStatus taskStatus, User user, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
        this.taskStatus = taskStatus;
        this.user = user;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static TaskResponse of(Task task) {
        return TaskResponse.builder()
                .taskId(task.getTaskId())
                .taskTitle(task.getTaskTitle())
                .taskContent(task.getTaskContent())
                .taskStatus(task.getTaskStatus())
                .user(task.getUser())
                .createdDate(task.getCreatedDate())
                .modifiedDate(task.getModifiedDate())
                .build();
    }
}
