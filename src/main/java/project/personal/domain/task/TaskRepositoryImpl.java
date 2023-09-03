package project.personal.domain.task;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import project.personal.api.service.todo.request.TaskFindServiceRequest;
import project.personal.api.service.todo.response.TaskResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static project.personal.domain.task.QTask.task;


@Slf4j
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<TaskResponse> findByCondition(TaskFindServiceRequest condition) {

        Long taskId = condition.getTaskId();
        String taskTitle = condition.getTaskTitle();
        String taskContent = condition.getTaskContent();
        TaskStatus taskStatus = condition.getTaskStatus();
        LocalDateTime searchStartDate = condition.getSearchStartDate();
        LocalDateTime searchEndDate = condition.getSearchEndDate();
        Long userId = condition.getUserId();

        return jpaQueryFactory.select(Projections.bean(TaskResponse.class,
                        task.taskId,
                        task.taskTitle,
                        task.taskContent,
                        task.taskStatus,
                        task.createdDate,
                        task.modifiedDate
                ))
                .from(task)
                .where(
                        taskIdCheck(taskId),
                        taskContentCheck(taskContent),
                        taskStatusCheck(taskStatus),
                        taskCreatedDateCheck(searchStartDate, searchEndDate),
                        taskUserIdCheck(userId),
                        taskTitleCheck(taskTitle)
                )
                .fetch();
    }

    public BooleanExpression taskIdCheck(Long taskId) {
        if (taskId != null) {
            return task.taskId.eq(taskId);
        }
        return null;
    }

    public BooleanExpression taskUserIdCheck(Long userId) {
        if (userId != null) {
//            return todo.user.eq();
            return null;
        }
        return null;
    }

    /**
     * 입력 날짜가 없을 경우 오늘 날짜로부터 30일 전에 생성된 todo만 조회
     * @param
     * @return
     */
    public BooleanExpression taskCreatedDateCheck(LocalDateTime searchStartDate, LocalDateTime searchEndDate) {
        if (searchStartDate == null) searchStartDate = LocalDate.now().minusDays(30).atStartOfDay();
        if (searchEndDate == null) searchEndDate = LocalDateTime.now();
        return task.createdDate.between(searchStartDate, searchEndDate);
    }
    public BooleanExpression taskStatusCheck(TaskStatus taskStatus) {
        if (taskStatus != null) {
            return task.taskStatus.eq(taskStatus);
        }
        return null;
    }
    public BooleanExpression taskTitleCheck(String taskTitle) {
        if (StringUtils.hasText(taskTitle)) {
            return task.taskTitle.like("%" + taskTitle + "%");
        }
        return null;
    }
    public BooleanExpression taskContentCheck(String taskContent) {
        if (StringUtils.hasText(taskContent)) {
            return task.taskContent.like("%" + taskContent + "%");
        }
        return null;
    }

}
