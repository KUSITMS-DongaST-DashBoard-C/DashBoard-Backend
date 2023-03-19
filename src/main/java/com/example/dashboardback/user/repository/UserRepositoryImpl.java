package com.example.dashboardback.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static com.example.dashboardback.loginhistory.entity.QLoginHistory.loginHistory;
import static com.example.dashboardback.user.entity.QUser.user;

public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public long getSignUpNumByDay(int start, int end) {
        LocalDateTime today = LocalDateTime.of(2023,03,28,17,00,00,00).minusDays(start);
        LocalDateTime daysAgo=today.minusDays(end).truncatedTo(ChronoUnit.DAYS);

        return queryFactory.selectFrom(user)
                .where(user.createdAt.between(daysAgo, today))
                .fetchCount();
    }
}
