package project.personal.domain.history.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.personal.domain.todo.Todo;

@Repository
public interface TodoHistoryRepository extends JpaRepository<Todo, Long>, TodoHistoryRepositoryCustom {
}
