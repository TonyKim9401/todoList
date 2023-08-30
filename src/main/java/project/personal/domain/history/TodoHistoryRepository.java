package project.personal.domain.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.personal.domain.todo.Todo;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoHistoryRepository extends JpaRepository<Todo, Long>, TodoHistoryRepositoryCustom {
}
