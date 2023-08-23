package project.personal.api.controller.todo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.personal.api.ApiResponse;
import project.personal.api.controller.todo.request.TodoCreateRequest;
import project.personal.api.service.todo.TodoService;
import project.personal.api.service.todo.response.TodoResponse;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/api/v1/orders/new")
    public ApiResponse<TodoResponse> createOrder(@Valid @RequestBody TodoCreateRequest request) {
        LocalDateTime registeredDateTime =  LocalDateTime.now();
        TodoResponse order = todoService.createTodo(registeredDateTime);
        return ApiResponse.ok(order);
    }
}
