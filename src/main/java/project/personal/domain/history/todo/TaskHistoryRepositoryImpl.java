package project.personal.domain.history.todo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TaskHistoryRepositoryImpl implements TaskHistoryRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

}
