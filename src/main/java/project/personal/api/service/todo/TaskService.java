package project.personal.api.service.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.personal.api.service.todo.request.TaskCreateServiceRequest;
import project.personal.api.service.todo.request.TaskUpdateServiceRequest;
import project.personal.api.service.todo.response.TaskResponse;
import project.personal.domain.history.todo.TaskHistory;
import project.personal.domain.history.todo.TaskHistoryRepository;
import project.personal.domain.task.Task;
import project.personal.domain.task.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskHistoryRepository taskHistoryRepository;
    /**
     * 동시성 고민
     * optimistic lock / pessimistic lock / ...
     */
    public TaskResponse createTask(TaskCreateServiceRequest request) {
        Task todo = Task.create(request.toTaskEntity(), null);
        Task saveTask = taskRepository.save(todo);

        saveTaskHistory(saveTask);

        return TaskResponse.of(saveTask);
    }

    public TaskResponse updateTask(Long taskId, TaskUpdateServiceRequest request) {
        Task task = null;
        Optional<Task> findTask = taskRepository.findById(taskId);
        if (findTask.isPresent()) {
            task = findTask.get();
            task.update(request);
        } else {
            throw new IllegalArgumentException("해당 id가 존재하지 않습니다. id : " + request.getTaskId());
        }
        return TaskResponse.of(task);
    }

    public TaskResponse findTaskById(Long taskId) {
        Task findTask = taskRepository.findById(taskId).orElseThrow(
                () -> new IllegalArgumentException("No todoId has found")
        );
        return TaskResponse.of(findTask);
    }

    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(t -> TaskResponse.of(t))
                .collect(Collectors.toList());
    }

    public void deleteTask(Long id) {
        Optional<Task> findTask = taskRepository.findById(id);

        if (findTask.isPresent()) {
            taskRepository.deleteById(id);
            saveTaskHistory(findTask.get());
        } else {
            throw new IllegalArgumentException("해당 id가 존재하지 않습니다. id : " + id);
        }
    }

    /**
     * Task의 Insert, Update 처리 후 TaskHistory 에 이력을 저장한다.
     * @param task
     */
    public void saveTaskHistory(Task task) {
        TaskHistory taskHistory = TaskHistory.create(task, null);
        taskHistoryRepository.save(taskHistory);
    }

    /**
     * List<Todo> 의 Insert, Update 처리 후 TodoHistory 에 이력을 저장한다.
     * @param taskList
     */
    public void saveTaskHistories(List<Task> taskList) {
        List<TaskHistory> taskHistories = taskList.stream()
                                                    .map(todo -> TaskHistory.create(todo, null))
                                                    .toList();
        taskHistoryRepository.saveAll(taskHistories);
    }

}
