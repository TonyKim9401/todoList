package project.personal.domain.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, TaskRepositoryCustom {

    @Query("select t from Task t where t.createdDate >= :startDate " +
            "and t.createdDate <= :endDate")
    List<Task> findTaskBetweenStartTimeAndEndTime(@Param("startDate") LocalDateTime startDate,
                             @Param("endDate") LocalDateTime endDate);
}
