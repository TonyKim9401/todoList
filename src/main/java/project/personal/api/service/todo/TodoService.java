package project.personal.api.service.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.personal.api.service.todo.request.TodoCreateServiceRequest;
import project.personal.api.service.todo.response.TodoResponse;
import project.personal.domain.todo.Todo;
import project.personal.domain.todo.TodoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;


    /**
     * 동시성 고민
     * optimistic lock / pessimistic lock / ...
     */
    public TodoResponse createTodo(TodoCreateServiceRequest request) {
        Todo todo = Todo.create(request.getTodoTitle(), request.getTodoContent());
        Todo saveTodo = todoRepository.save(todo);
        return TodoResponse.of(saveTodo);
    }

    public TodoResponse findTodoById(Long todoId) {
        Todo findTodo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("No todoId has found")
        );
        return TodoResponse.of(findTodo);
    }

    public List<TodoResponse> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map(t -> TodoResponse.of(t))
                .collect(Collectors.toList());
    }

}
