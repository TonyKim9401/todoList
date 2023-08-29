package project.personal.api.service.todo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.personal.domain.BaseEntity;
import project.personal.domain.todo.TodoStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TodoFindServiceRequest extends BaseEntity {

    private Long id;
    private String todoTitle;
    private String todoContent;
    private Long userId;
    private TodoStatus todoStatus;

    private LocalDateTime searchStartDate;
    private LocalDateTime searchEndDate;

    @Builder
    private TodoFindServiceRequest(Long id, String todoTitle, String todoContent, Long userId, TodoStatus todoStatus, LocalDateTime searchStartDate, LocalDateTime searchEndDate) {
        this.id = id;
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.userId = userId;
        this.todoStatus = todoStatus;
        this.searchStartDate = searchStartDate;
        this.searchEndDate = searchEndDate;
    }


    public static TodoFindServiceRequest create(Long id, String todoTitle, String todoContent, Long userId, TodoStatus todoStatus, LocalDateTime searchStartDate, LocalDateTime searchEndDate) {
        return TodoFindServiceRequest.builder()
                .id(id)
                .todoTitle(todoTitle)
                .todoContent(todoContent)
                .userId(userId)
                .todoStatus(todoStatus)
                .searchStartDate(searchStartDate)
                .searchEndDate(searchEndDate)
                .build();
    }

}
