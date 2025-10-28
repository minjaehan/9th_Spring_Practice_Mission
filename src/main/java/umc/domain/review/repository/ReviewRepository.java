package umc.domain.review.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Transactional
    @Query(value = """
            INSERT  INTO  review
            (content, star, user_id, store_id ,created_at, updat    ed_at)
            VALUES
                    (:content, :userId, :memberId, : storeId, NOW(),NOW())
    """, nativeQuery = true)

    int insertReviewNative(@Param("content") String content,
                           @Param("star") Float star,
                           @Param("memberId") Long memberId,
                           @Param("storeId") Long storeId);

}
