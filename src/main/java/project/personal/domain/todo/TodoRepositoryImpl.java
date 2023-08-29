package project.personal.domain.todo;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import project.personal.api.service.todo.request.TodoFindServiceRequest;
import project.personal.api.service.todo.response.TodoResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static project.personal.domain.todo.QTodo.todo;

@Slf4j
@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<TodoResponse> findByCondition(TodoFindServiceRequest condition) {
        Long todoId = condition.getId();
        String todoTitle = condition.getTodoTitle();
        String todoContent = condition.getTodoContent();
        TodoStatus todoStatus = condition.getTodoStatus();
        LocalDateTime searchStartDate = condition.getSearchStartDate();
        LocalDateTime searchEndDate = condition.getSearchEndDate();
        Long userId = condition.getUserId();

        return jpaQueryFactory.select(Projections.bean(TodoResponse.class,
                        todo.id,
                        todo.todoTitle,
                        todo.todoContent,
                        todo.todoStatus,
                        todo.createdDate,
                        todo.modifiedDate
                ))
                .from(todo)
                .where(
                        todoIdCheck(todoId),
                        todoContentCheck(todoContent),
                        todoStatusCheck(todoStatus),
                        todoCreatedDateCheck(searchStartDate, searchEndDate),
                        todoUserIdCheck(userId),
                        todoTitleCheck(todoTitle)
                )
                .fetch();
    }

    public BooleanExpression todoIdCheck(Long todoId) {
        if (todoId != null) {
            return todo.id.eq(todoId);
        }
        return null;
    }

    public BooleanExpression todoUserIdCheck(Long userId) {
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
    public BooleanExpression todoCreatedDateCheck(LocalDateTime searchStartDate, LocalDateTime searchEndDate) {
        if (searchStartDate == null) searchStartDate = LocalDate.now().minusDays(30).atStartOfDay();
        if (searchEndDate == null) searchEndDate = LocalDateTime.now();
        return todo.createdDate.between(searchStartDate, searchEndDate);
    }
    public BooleanExpression todoStatusCheck(TodoStatus todoStatus) {
        if (todoStatus != null) {
            return todo.todoStatus.eq(todoStatus);
        }
        return null;
    }
    public BooleanExpression todoTitleCheck(String todoTitle) {
        if (StringUtils.hasText(todoTitle)) {
            return todo.todoTitle.like("%" + todoTitle + "%");
        }
        return null;
    }
    public BooleanExpression todoContentCheck(String todoContent) {
        if (StringUtils.hasText(todoContent)) {
            return todo.todoContent.like("%" + todoContent + "%");
        }
        return null;
    }

}
