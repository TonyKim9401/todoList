package project.personal.api.controller.todo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.personal.api.ApiResponse;
import project.personal.api.controller.todo.request.TodoCreateRequest;
import project.personal.api.controller.todo.request.TodoUpdateRequest;
import project.personal.api.service.todo.TodoService;
import project.personal.api.service.todo.response.TodoResponse;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/api/v1/todo/create/todo")
    public ApiResponse<TodoResponse> createTodo(@Valid @RequestBody TodoCreateRequest request) {
        TodoResponse todoResponse = todoService.createTodo(request.toServiceRequest(null));
        return ApiResponse.ok(todoResponse);
    }

    @GetMapping("/api/v1/todo/{todoId}")
    public ApiResponse<TodoResponse> getTodo(@PathVariable Long todoId) {
        TodoResponse findTodo = todoService.findTodoById(todoId);
        return  ApiResponse.ok(findTodo);
    }

    @GetMapping("/api/v1/todo/all")
    public ApiResponse<List<TodoResponse>> getAllTodos() {
        List<TodoResponse> todoResponseList = todoService.getAllTodos();
        return ApiResponse.ok(todoResponseList);
    }

    @PutMapping("/api/v1/todo/update/{todoId}")
    public ApiResponse<TodoResponse> updateTodo(@PathVariable Long todoId, @RequestBody TodoUpdateRequest request) {
        // update시 user가 사용되지 않을것 같음
        TodoResponse todoResponse = todoService.updateTodo(request.toUpdateServiceRequest());
        return ApiResponse.ok(todoResponse);
    }



}
