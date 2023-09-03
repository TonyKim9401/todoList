package project.personal.domain.history.todo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import project.personal.domain.BaseEntity;
import project.personal.domain.task.Task;
import project.personal.domain.task.TaskStatus;
import project.personal.domain.user.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class TaskHistory extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskHistoryId;

    private String taskTitle;

    private String taskContent;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    private TaskHistory(String taskTitle, String taskContent, TaskStatus taskStatus, Task task, User user) {
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
        this.taskStatus = taskStatus;
        this.task = task;
        this.user = user;
    }


    public static TaskHistory create(Task task, User user) {
        return TaskHistory.builder()
                .taskTitle(task.getTaskTitle())
                .taskContent(task.getTaskContent())
                .taskStatus(task.getTaskStatus())
                .task(task)
                .user(user)
                .build();
    }

}
