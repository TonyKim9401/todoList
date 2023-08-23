package project.personal.domain.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("select t from Todo t where t.registeredDateTime >= :startDateTime " +
            "and t.registeredDateTime < :endDateTime " +
            "and t.todoStatus = :todoStatus")
    List<Todo> findTodoBy(@Param("startDateTime") LocalDateTime startDateTime,
                             @Param("endDateTime") LocalDateTime endDateTime,
                             @Param("todoStatus") TodoStatus todoStatus);
}
