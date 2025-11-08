package umc.domain.review.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.domain.review.dto.MyReviewQuery;
import umc.domain.review.dto.QReviewCard;
import umc.domain.review.dto.ReviewCard;

import java.util.List;

import static umc.domain.review.entity.QReview.review;
import static umc.domain.store.entity.QStore.store;

@Repository
@RequiredArgsConstructor
public class ReviewQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<ReviewCard> findMyReviews(Long memberId, MyReviewQuery req, Pageable pageable) {

        BooleanBuilder where = new BooleanBuilder()
                .and(review.member.id.eq(memberId));


        if (req.storeId() != null) where.and(review.store.id.eq(req.storeId()));
        if (req.star() != null) {
            where.and(review.star.eq(req.star()));
        } else {
            if (req.minStar() != null) where.and(review.star.goe(req.minStar()));
            if (req.maxStar() != null) where.and(review.star.loe(req.maxStar()));
        }

        OrderSpecifier<?>[] orders = switch (req.sortKey()) {
            case STAR_DESC -> new OrderSpecifier[]{review.star.desc(), review.id.desc()};
            case STAR_ASC  -> new OrderSpecifier[]{review.star.asc(), review.id.desc()};
            case CREATED_DESC -> new OrderSpecifier[]{review.createdAt.desc(), review.id.desc()};
        };

        List<ReviewCard> content = queryFactory
                .select(new QReviewCard(
                        review.id,
                        review.store.id,
                        review.store.name,
                        review.star,
                        review.content,
                        review.createdAt,
                        review.member.id.eq(memberId)
                ))
                .from(review)
                .join(review.store, store)
                .where(where)
                .orderBy(orders)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .where(where)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}
