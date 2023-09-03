package project.personal.api.service.todo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.personal.domain.BaseEntity;
import project.personal.domain.task.TaskStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TaskFindServiceRequest extends BaseEntity {

    private Long taskId;
    private String taskTitle;
    private String taskContent;
    private Long userId;
    private TaskStatus taskStatus;

    private LocalDateTime searchStartDate;
    private LocalDateTime searchEndDate;

    @Builder
    private TaskFindServiceRequest(Long taskId, String taskTitle, String taskContent, Long userId, TaskStatus taskStatus, LocalDateTime searchStartDate, LocalDateTime searchEndDate) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
        this.userId = userId;
        this.taskStatus = taskStatus;
        this.searchStartDate = searchStartDate;
        this.searchEndDate = searchEndDate;
    }


    public static TaskFindServiceRequest create(Long taskId, String taskTitle, String taskContent, Long userId, TaskStatus taskStatus, LocalDateTime searchStartDate, LocalDateTime searchEndDate) {
        return TaskFindServiceRequest.builder()
                .taskId(taskId)
                .taskContent(taskContent)
                .taskStatus(taskStatus)
                .taskTitle(taskTitle)
                .userId(userId)
                .searchStartDate(searchStartDate)
                .searchEndDate(searchEndDate)
                .build();
    }

}
