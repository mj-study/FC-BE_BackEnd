package org.fastcampus.post.application;

import static org.junit.jupiter.api.Assertions.*;

import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdateCommentRequestDto;
import org.fastcampus.post.domain.comment.Comment;
import org.junit.jupiter.api.Test;

class CommentServiceTest extends PostApplicationTestTemplate {

    @Test
    void givenCreateCommentRequestDto_whenCreateComment_thenReturnComment() {
        //when
        Comment comment = commentService.createComment(commentRequestDto);

        //then
        assertEquals(commentContent, comment.getContent());
    }


    @Test
    void givenUpdateCommentRequestDto_whenUpdateComment_thenReturnUpdatedComment() {
        // Given
        String updatedContent = "updated comment content";
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(
            user.getId(), updatedContent);

        // When
        Comment updatedComment = commentService.updateComment(comment.getId(),
            updateCommentRequestDto);

        // Then
        assertEquals(updatedContent, updatedComment.getContent());
    }


    @Test
    void givenCommentIdAndUserId_whenLikeComment_thenIncreaseLikeCount() {
        // Given
        Long commentId = comment.getId();
        Long userId = otherUser.getId();

        // When
        LikeRequestDto likeRequestDto = new LikeRequestDto(commentId, userId);
        commentService.likeComment(likeRequestDto);
        Comment likedComment = commentService.getComment(commentId);

        // Then
        assertEquals(1, likedComment.getLikeCount());
    }

    @Test
    void givenCommentIdAndUserId_whenUnLikeComment_thenDecreaseLikeCount() {
        // Given
        Long commentId = comment.getId();
        Long userId = otherUser.getId();
        LikeRequestDto likeRequestDto = new LikeRequestDto(commentId, userId);
        commentService.likeComment(likeRequestDto);

        // When
        commentService.unlikeComment(likeRequestDto);
        Comment unlikedComment = commentService.getComment(commentId);

        // Then
        assertEquals(0, unlikedComment.getLikeCount());
    }

}