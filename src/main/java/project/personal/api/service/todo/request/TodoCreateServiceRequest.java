package project.personal.api.service.todo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.personal.domain.todo.Todo;
import project.personal.domain.user.User;

@Getter
@NoArgsConstructor
public class TodoCreateServiceRequest {

    private String todoTitle;
    private String todoContent;
    private User user;

    @Builder
    private TodoCreateServiceRequest(String todoTitle, String todoContent, User user) {
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.user = user;
    }

    public Todo toTodoEntity() {
        return Todo.builder()
                .todoTitle(todoTitle)
                .todoContent(todoContent)
                .build();
    }
}
