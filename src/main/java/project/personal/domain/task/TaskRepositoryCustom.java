package project.personal.domain.task;

import project.personal.api.service.todo.request.TaskFindServiceRequest;
import project.personal.api.service.todo.response.TaskResponse;

import java.util.List;

public interface TaskRepositoryCustom {

    List<TaskResponse> findByCondition(TaskFindServiceRequest condition);

}
