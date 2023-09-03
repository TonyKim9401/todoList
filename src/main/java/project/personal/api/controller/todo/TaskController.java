package project.personal.api.controller.todo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.personal.api.ApiResponse;
import project.personal.api.controller.todo.request.TaskCreateRequest;
import project.personal.api.controller.todo.request.TaskUpdateRequest;
import project.personal.api.service.todo.TaskService;
import project.personal.api.service.todo.response.TaskResponse;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/api/v1/task/create/task")
    public ApiResponse<TaskResponse> createTodo(@Valid @RequestBody TaskCreateRequest request) {
        TaskResponse taskResponse = taskService.createTask(request.toServiceRequest(null));
        return ApiResponse.ok(taskResponse);
    }

    @GetMapping("/api/v1/task/{taskId}")
    public ApiResponse<TaskResponse> getTodo(@PathVariable Long taskId) {
        TaskResponse findTask = taskService.findTaskById(taskId);
        return  ApiResponse.ok(findTask);
    }

    @GetMapping("/api/v1/task/all")
    public ApiResponse<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> taskResponseList = taskService.getAllTasks();
        return ApiResponse.ok(taskResponseList);
    }

    @PutMapping("/api/v1/task/update/{taskId}")
    public ApiResponse<TaskResponse> updateTask(@PathVariable Long taskId, @RequestBody TaskUpdateRequest request) {
        // update시 user가 사용되지 않을것 같음
        TaskResponse taskResponse = taskService.updateTask(taskId, request.toUpdateServiceRequest());
        return ApiResponse.ok(taskResponse);
    }

    @DeleteMapping("/api/v1/task/delete/{taskId}")
    public ApiResponse<String> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ApiResponse.ok("SUCCESS");
    }


}
