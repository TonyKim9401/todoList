package project.personal.domain.todo;

import project.personal.api.service.todo.request.TodoFindServiceRequest;
import project.personal.api.service.todo.response.TodoResponse;

import java.util.List;

public interface TodoRepositoryCustom {

    List<TodoResponse> findByCondition(TodoFindServiceRequest condition);

}
