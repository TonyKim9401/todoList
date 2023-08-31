package project.personal.domain.history.todo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.personal.domain.BaseEntity;
import project.personal.domain.todo.Todo;
import project.personal.domain.todo.TodoStatus;
import project.personal.domain.user.User;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class TodoHistory extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoHistoryId;

    private String todoTitle;

    private String todoContent;

    @Enumerated(EnumType.STRING)
    private TodoStatus todoStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Todo todo;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;




    @Builder
    private TodoHistory(String todoTitle, String todoContent, TodoStatus todoStatus) {
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.todoStatus = todoStatus;
    }


    public static TodoHistory create(Todo todo) {
        return TodoHistory.builder()
                .todoTitle(todo.getTodoTitle())
                .todoContent(todo.getTodoContent())
                .todoStatus(todo.getTodoStatus())
                .build();
    }

}
