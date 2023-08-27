package project.personal.domain.todo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.personal.domain.BaseEntity;
import project.personal.domain.user.User;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String todoTitle;

    private String todoContent;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private TodoStatus todoStatus;



    @Builder
    private Todo(String todoTitle, String todoContent, TodoStatus todoStatus) {
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.todoStatus = todoStatus;
    }


    public static Todo create(String todoTitle, String todoContent) {
        return Todo.builder()
                .todoTitle(todoTitle)
                .todoContent(todoContent)
                .todoStatus(TodoStatus.CREATE)
                .build();
    }

    public void todoCompleted() {
        this.todoStatus = TodoStatus.COMPLETE;
    }

    public void todoStart() {
        this.todoStatus = TodoStatus.START;
    }

}
