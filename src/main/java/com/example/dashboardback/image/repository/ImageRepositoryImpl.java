package com.example.dashboardback.image.repository;

import com.example.dashboardback.image.entity.Image;
import com.example.dashboardback.image.entity.QImage;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.example.dashboardback.image.entity.QImage.image;

public class ImageRepositoryImpl implements ImageRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ImageRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Image> findNotDeletedByImageId(Long imageId) {
        return Optional.ofNullable(queryFactory.selectFrom(image)
                .where(imageIdEq(imageId),
                        isDeletedCheck())
                .fetchFirst());
    }

    @Override
    public String findByUserId(Long userId) {
        return queryFactory.select(image.imageUrl)
                .where(image.admin.adminId.eq(userId))
                .from(image)
                .fetchFirst();
    }


    private BooleanExpression isDeletedCheck() {
        return image.isDeleted.eq(false);
    }

    private BooleanExpression imageIdEq(Long imageId) {
        return imageId != null ? image.imageId.eq(imageId) : null;
    }
}

