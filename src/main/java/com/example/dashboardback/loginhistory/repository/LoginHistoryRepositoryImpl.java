package com.example.dashboardback.loginhistory.repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import static com.example.dashboardback.loginhistory.entity.QLoginHistory.loginHistory;
import static com.example.dashboardback.user.entity.QUser.user;

public class LoginHistoryRepositoryImpl implements LoginHistoryCustom{

    private final JPAQueryFactory queryFactory;
    public LoginHistoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public long getDauByDay(int day) {
        LocalDate targetDate = LocalDate.parse("2023-03-28", DateTimeFormatter.ISO_LOCAL_DATE).minusDays(day);
        Date sqlTargetDate = Date.valueOf(targetDate);

        return queryFactory.select(user.userId)
                .distinct()
                .from(user, loginHistory)
                .where(user.eq(loginHistory.user)
                        .and(Expressions.dateTemplate(Date.class, "DATE({0})", loginHistory.loginTime).eq(sqlTargetDate)))
                .fetchCount();
    }

    @Override
    public long getMauByMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate monthStart = yearMonth.atDay(1);
        LocalDate monthEnd = yearMonth.atEndOfMonth();

        return queryFactory.select(user.userId)
                .distinct()
                .from(user, loginHistory)
                .join(loginHistory.user, user)
                .where(loginHistory.loginTime.between(monthStart.atStartOfDay(), monthEnd.atTime(23, 59, 59)))
                .fetchCount();

    }

    @Override
    public long getWauByWeek(LocalDateTime time) {
        LocalDateTime weekEnd = time.toLocalDate().atTime(23,59,59);
        LocalDateTime weekStart = weekEnd.minusWeeks(1).plusDays(1);

        return queryFactory.select(user.userId)
                .distinct()
                .from(user, loginHistory)
                .join(loginHistory.user, user)
                .where(loginHistory.loginTime.between(weekStart, weekEnd))
                .fetchCount();

    }
}
