package project.personal.api.service.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.personal.api.service.todo.request.TodoCreateServiceRequest;
import project.personal.api.service.todo.request.TodoUpdateServiceRequest;
import project.personal.api.service.todo.response.TodoResponse;
import project.personal.domain.history.todo.TodoHistory;
import project.personal.domain.history.todo.TodoHistoryRepository;
import project.personal.domain.todo.Todo;
import project.personal.domain.todo.TodoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    private final TodoHistoryRepository todoHistoryRepository;

    /**
     * 동시성 고민
     * optimistic lock / pessimistic lock / ...
     */
    public TodoResponse createTodo(TodoCreateServiceRequest request) {
        Todo todo = Todo.create(request.toTodoEntity(), null);
        Todo saveTodo = todoRepository.save(todo);

        saveTodoHistory(saveTodo);

        return TodoResponse.of(saveTodo);
    }

    public TodoResponse updateTodo(TodoUpdateServiceRequest request) {
        Todo todo = null;
        Optional<Todo> findTodo = todoRepository.findById(request.getTodoId());
        if (findTodo.isPresent()) {
            todo = findTodo.get();
            todo.update(request);
        } else {
            throw new IllegalArgumentException("해당 id가 존재하지 않습니다. id : " + request.getTodoId());
        }
        return TodoResponse.of(todo);
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

    public void deleteTodo(Long id) {
        Optional<Todo> findTodo = todoRepository.findById(id);

        if (findTodo.isPresent()) {
            todoRepository.deleteById(id);
            saveTodoHistory(findTodo.get());
        } else {
            throw new IllegalArgumentException("해당 id가 존재하지 않습니다. id : " + id);
        }
    }

    /**
     * Todo의 Insert, Update 처리 후 TodoHistory 에 이력을 저장한다.
     * @param todo
     */
    public void saveTodoHistory(Todo todo) {
        TodoHistory todoHistory = TodoHistory.create(todo, null);
        todoHistoryRepository.save(todoHistory);
    }

    /**
     * List<Todo> 의 Insert, Update 처리 후 TodoHistory 에 이력을 저장한다.
     * @param todoList
     */
    public void saveTodoHistories(List<Todo> todoList) {
        List<TodoHistory> todoHistories = todoList.stream()
                                                    .map(todo -> TodoHistory.create(todo, null))
                                                    .toList();
        todoHistoryRepository.saveAll(todoHistories);
    }

}
