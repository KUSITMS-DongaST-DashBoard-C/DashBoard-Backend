package com.example.dashboardback.user.repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static com.example.dashboardback.loginhistory.entity.QLoginHistory.loginHistory;
import static com.example.dashboardback.user.entity.QUser.user;

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
}
