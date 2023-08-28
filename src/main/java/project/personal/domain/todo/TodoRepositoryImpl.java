package project.personal.domain.todo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.personal.api.service.todo.request.TodoFindServiceRequest;
import project.personal.api.service.todo.response.TodoResponse;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<TodoResponse> findByCondition(TodoFindServiceRequest condition) {
        log.info("reached here!!!");
        return null;
    }
}
