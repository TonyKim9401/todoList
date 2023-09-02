package project.personal.domain.todo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.personal.api.service.todo.request.TodoUpdateServiceRequest;
import project.personal.domain.BaseEntity;
import project.personal.domain.user.User;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Todo extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    private String todoTitle;

    private String todoContent;
    @Enumerated(EnumType.STRING)
    private TodoStatus todoStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    private Todo(String todoTitle, String todoContent, TodoStatus todoStatus, User user) {
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.todoStatus = todoStatus;
        this.user = user;
    }


    public static Todo create(Todo todo, User user) {
        return Todo.builder()
                .todoTitle(todo.getTodoTitle())
                .todoContent(todo.getTodoContent())
                .todoStatus(TodoStatus.CREATE)
                .user(user)
                .build();
    }

    public void todoCompleted() {
        this.todoStatus = TodoStatus.COMPLETE;
    }

    public void todoStart() {
        this.todoStatus = TodoStatus.START;
    }

    public void update(TodoUpdateServiceRequest request) {
        this.todoTitle = request.getTodoTitle();
        this.todoContent = request.getTodoContent();
        this.todoStatus = request.getTodoStatus();
        // user는 바뀔 일이 없을것 같음
    }
}
