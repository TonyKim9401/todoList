package project.personal.api.controller.todo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.personal.api.ApiResponse;
import project.personal.api.controller.todo.request.TodoCreateRequest;
import project.personal.api.service.todo.TodoService;
import project.personal.api.service.todo.response.TodoResponse;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/api/v1/todo/create/todo")
    public ApiResponse<TodoResponse> createOrder(@Valid @RequestBody TodoCreateRequest request) {
        TodoResponse todoResponse = todoService.createTodo(request.toServiceRequest());
        return ApiResponse.ok(todoResponse);
    }

    @GetMapping("/api/v1/todo/all")
    public ApiResponse<List<TodoResponse>> getAllTodos() {
        List<TodoResponse> todoResponseList = todoService.getAllTodos();
        return ApiResponse.ok(todoResponseList);
    }




}
