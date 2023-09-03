package project.personal.domain.task;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import project.personal.api.service.todo.request.TaskUpdateServiceRequest;
import project.personal.domain.BaseEntity;
import project.personal.domain.user.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Task extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String taskTitle;

    private String taskContent;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    private Task(String taskTitle, String taskContent, TaskStatus taskStatus, User user) {
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
        this.taskStatus = taskStatus;
        this.user = user;
    }


    public static Task create(Task task, User user) {
        return Task.builder()
                .taskTitle(task.getTaskTitle())
                .taskContent(task.getTaskContent())
                .taskStatus(TaskStatus.CREATE)
                .user(user)
                .build();
    }

    public void taskCompleted() {
        this.taskStatus = TaskStatus.COMPLETE;
    }

    public void taskStart() {
        this.taskStatus = TaskStatus.START;
    }

    public void update(TaskUpdateServiceRequest request) {
        this.taskTitle = request.getTaskTitle();
        this.taskContent = request.getTaskContent();
        this.taskStatus = request.getTodoStatus();
        // user는 바뀔 일이 없을것 같음
    }
}
