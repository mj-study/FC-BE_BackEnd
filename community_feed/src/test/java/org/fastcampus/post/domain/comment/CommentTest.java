package org.fastcampus.post.domain.comment;

import static org.junit.jupiter.api.Assertions.*;

import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommentTest {

    private final UserInfo userInfo = new UserInfo("name", "url");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, userInfo);

    private final Post post = new Post(1L, user, new PostContent("content"));
    private final Comment comment = new Comment(1L, post, user, new CommentContent("content"));

    @Test
    void givenCommentLike_whenLike_thenLikeCountShouldBe1() {
        //when
        comment.like(otherUser);

        //then
        assertEquals(1, comment.getLikeCount());
    }


    @Test
    void givenCommentLike_whenLikedByAuthor_thenShouldThrowException() {
        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            comment.like(user);
        });
    }


    @Test
    void givenCommentLikeAndUnlike_whenUnlike_thenLikeCountShouldBe0() {
        // when
        comment.like(otherUser);
        comment.unLike(otherUser);

        // then
        assertEquals(0, comment.getLikeCount());
    }


    @Test
    void givenCommentUpdate_whenUpdateComment_thenContentShouldBeUpdated() {
        // given
        String newContent = "updated content";

        // when
        comment.updateComment(user, newContent);

        // then
        assertEquals(newContent, comment.getContent());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "가"})
    void testCommentContentTooLongParameterized(String word) {
        // given
        String expectedContent = word.repeat(101);

        // when
        // then
        assertThrows(IllegalArgumentException.class,
            () -> new Comment(99L, post, user, new CommentContent(expectedContent)));
    }

}