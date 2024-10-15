package org.fastcampus.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.domain.Post;
import org.junit.jupiter.api.Test;

class PostServiceTest extends PostApplicationTestTemplate {

    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost() {
        //when
        Post savedPost = postService.createPost(postRequestDto);

        //then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(savedPost.getId(), post.getId());
    }

    @Test
    void givenCreatePost_whenUpdate_thenReturnUpdatedPost() {
        //given
        Post savedPost = postService.createPost(postRequestDto);
        //when
        Post post = postService.updatePost(savedPost.getId(), postUpdateDto);
        //then
        assertEquals(savedPost.getId(), post.getId());
        assertEquals(savedPost.getContent(), post.getContent());
        assertEquals(savedPost.getAuthor(), post.getAuthor());
    }

    @Test
    void givenCreatePost_whenLiked_thenReturnPostWithLike() {
        //given
        Post post = postService.createPost(postRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(post.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        //then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenCreatePostLiked_whenUnlike_thenReturnWithoutLike() {
        //given
        Post post = postService.createPost(postRequestDto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(post.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        //when
        postService.unlikePost(likeRequestDto);

        //then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenCreatePost_whenUnliked_thenReturnWithoutLike() {
        //given
        Post post = postService.createPost(postRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(post.getId(), otherUser.getId());
        postService.unlikePost(likeRequestDto);

        //then
        assertEquals(0, post.getLikeCount());
    }
}