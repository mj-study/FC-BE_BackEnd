package org.fastcampus.post.repository.jpa;

import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query(value = "UPDATE CommentEntity c "
        + "SET c.content = :#{#entity.getContent()}, "
        + "c.updDt = now() "
        + "WHERE c.id = :#{#entity.getId()} ")
    void updateCommentEntity(CommentEntity entity);

    @Modifying
    @Query(value = "UPDATE CommentEntity c "
        + "SET c.likeCount = :#{#comment.getLikeCount()}, "
        + "c.updDt = now() "
        + "WHERE c.id = :#{#comment.getId()} ")
    void updateLikeCount(CommentEntity comment);
}
