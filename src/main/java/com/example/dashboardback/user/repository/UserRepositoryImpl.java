package com.example.dashboardback.user.repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


import static com.example.dashboardback.loginhistory.entity.QLoginHistory.loginHistory;
import static com.example.dashboardback.user.entity.QUser.user;

@Slf4j
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public long getSignUpNumByDay(int day) {
        LocalDate targetDate = LocalDate.parse("2023-03-28", DateTimeFormatter.ISO_LOCAL_DATE).minusDays(day);
        Date sqlTargetDate = Date.valueOf(targetDate);

        return queryFactory.selectFrom(user)
                .where(Expressions.dateTemplate(Date.class, "DATE({0})", user.createdAt).eq(sqlTargetDate))
                .fetchCount();
    }

    @Override
    public long getSignUpNumByMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate monthStart = yearMonth.atDay(1);
        LocalDate monthEnd = yearMonth.atEndOfMonth();

        return queryFactory.selectFrom(user)
                .where(user.createdAt.between(monthStart.atStartOfDay(), monthEnd.atTime(23, 59, 59)))
                .fetchCount();
    }

    @Override
    public long getSignUpNumByWeek(LocalDateTime time) {
        LocalDateTime weekEnd = time.toLocalDate().atTime(23,59,59);
        LocalDateTime weekStart = weekEnd.minusWeeks(1).plusDays(1);

        return queryFactory.selectFrom(user)
                .where(user.createdAt.between(weekStart, weekEnd))
                .fetchCount();
    }


}
