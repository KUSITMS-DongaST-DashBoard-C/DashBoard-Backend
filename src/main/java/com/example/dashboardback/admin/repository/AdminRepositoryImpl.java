package com.example.dashboardback.admin.repository;

import com.example.dashboardback.admin.entity.Admin;
import com.example.dashboardback.admin.entity.QAdmin;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.example.dashboardback.admin.entity.QAdmin.admin;


public class AdminRepositoryImpl implements AdminRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public AdminRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return Optional.ofNullable(queryFactory.selectFrom(admin)
                .where(admin.email.eq(email))
                .from(admin)
                .fetchFirst());
    }
}

