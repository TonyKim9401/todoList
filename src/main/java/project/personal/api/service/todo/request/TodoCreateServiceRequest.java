package project.personal.api.service.todo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.personal.domain.user.User;

import java.util.List;

@Getter
@NoArgsConstructor
public class TodoCreateServiceRequest {

    private String todoTitle;
    private String todoContent;
    private User user;

    @Builder
    private TodoCreateServiceRequest(String todoTitle, String todoContent) {
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
    }
}
