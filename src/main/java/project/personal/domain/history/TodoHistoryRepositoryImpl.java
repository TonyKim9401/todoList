package project.personal.domain.history;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import project.personal.api.service.todo.request.TodoFindServiceRequest;
import project.personal.api.service.todo.response.TodoResponse;
import project.personal.domain.todo.TodoStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static project.personal.domain.todo.QTodo.todo;

@Slf4j
@RequiredArgsConstructor
public class TodoHistoryRepositoryImpl implements TodoHistoryRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

}
