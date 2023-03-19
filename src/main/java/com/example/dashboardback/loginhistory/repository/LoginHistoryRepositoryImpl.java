package com.example.dashboardback.loginhistory.repository;

import com.example.dashboardback.loginhistory.entity.QLoginHistory;
import com.example.dashboardback.user.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static com.example.dashboardback.loginhistory.entity.QLoginHistory.loginHistory;
import static com.example.dashboardback.user.entity.QUser.user;

public class LoginHistoryRepositoryImpl implements LoginHistoryCustom{

    private final JPAQueryFactory queryFactory;
    public LoginHistoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public long getDauByDay(int start, int end) {
        LocalDateTime today = LocalDateTime.of(2023,03,28,17,00,00,00).minusDays(start);
        LocalDateTime daysAgo=today.minusDays(end).truncatedTo(ChronoUnit.DAYS);

        return queryFactory.select(user.userId)
                .distinct()
                .from(user)
                .from(user, loginHistory)
                .where(user.eq(loginHistory.user)
                        .and(loginHistory.loginTime.between(daysAgo, today)))
                .fetchCount();
    }
}
