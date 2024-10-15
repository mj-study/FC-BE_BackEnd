package org.fastcampus.post.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class PostTest {

    private final UserInfo userInfo = new UserInfo("name", "url");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, userInfo);

    private final Post post = new Post(1L, user, new PostContent("content"));

    @Test
    void givenPostCreated_whenLike_thenLikeCountShouldBe1() {
        //given
        post.like(otherUser);
        //then
        assertEquals(1, post.getLikeCount());
    }


    @Test
    void givenPostCreated_whenUserLikesSelf_thenShouldThrowException() {
        //given & when & then
        assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }


    @Test
    void givenPostLikedAndUnliked_whenCheckLikeCount_thenShouldBe0() {
        // given
        post.like(otherUser);
        post.unLike();
        // then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostUnlikedInitially_whenCheckLikeCount_thenShouldBe0() {
        // when & then
        assertEquals(0, post.getLikeCount());
    }


    @Test
    void givenPostCreated_whenUpdatePost_thenContentShouldBeUpdated() {
        // given
        String newContent = "updated content";
        // when
        post.updatePost(user, newContent, PostPublicationState.PUBLIC);
        // then
        assertEquals(newContent, post.getContent());
    }


    @Test
    void givenPostCreated_whenOtherUserUpdatesPost_thenShouldThrowException() {
        // given
        String newContent = "new content";
        // when & then
        assertThrows(IllegalArgumentException.class,
            () -> post.updatePost(otherUser, newContent, PostPublicationState.PUBLIC));
    }
}