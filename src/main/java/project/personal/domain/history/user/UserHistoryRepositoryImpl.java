package project.personal.domain.history.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserHistoryRepositoryImpl implements UserHistoryRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


}
