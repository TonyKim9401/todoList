package project.personal.api.controller.todo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.personal.api.ApiResponse;
import project.personal.api.controller.todo.request.TodoCreateRequest;
import project.personal.api.service.todo.TodoService;
import project.personal.api.service.todo.response.TodoResponse;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/api/v1/todo/{todoId}")
    public ApiResponse<TodoResponse> findTodoById(@Valid @PathVariable Long todoId) {
        TodoResponse findTodo = todoService.findTodoById(todoId);
        return ApiResponse.ok(findTodo);
    }

    @GetMapping("/api/v1/todo")
    public ApiResponse<List<TodoResponse>> findAllTodo() {
        List<TodoResponse> todoList = todoService.findAll();
        return ApiResponse.ok(todoList);
    }

    @PostMapping("/api/v1/todo/new")
    public ApiResponse<TodoResponse> createTodo(@Valid @RequestBody TodoCreateRequest request) {
        LocalDateTime registeredDateTime =  LocalDateTime.now();
        TodoResponse saveTodo = todoService.createTodo(request.toServiceRequest(), registeredDateTime);
        return ApiResponse.ok(saveTodo);
    }


}
