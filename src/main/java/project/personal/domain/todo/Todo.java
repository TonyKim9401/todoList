package project.personal.domain.todo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.personal.domain.BaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Getter
@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TodoStatus todoStatus;

    private int totalPrice;

    private LocalDateTime registeredDateTime;



    @Builder
    private Todo(TodoStatus todoStatus, LocalDateTime registeredDateTime) {
        this.todoStatus = todoStatus;
        this.registeredDateTime = registeredDateTime;
    }


    public static Todo create(LocalDateTime registeredDateTime) {
        return Todo.builder()
                .todoStatus(TodoStatus.INIT)
                .registeredDateTime(registeredDateTime)
                .build();
    }

    public void todoCompleted(LocalDateTime time) {
        this.registeredDateTime = time;
        this.todoStatus = TodoStatus.COMPLETED;
    }
}
