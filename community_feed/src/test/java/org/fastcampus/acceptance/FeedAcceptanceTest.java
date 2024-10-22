package org.fastcampus.acceptance;

import static org.fastcampus.acceptance.steps.FeedAcceptanceSteps.requestCreatePost;
import static org.fastcampus.acceptance.steps.FeedAcceptanceSteps.requestFeed;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.fastcampus.acceptance.utils.AcceptanceTestTemplate;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.post.ui.dto.GetPostContentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FeedAcceptanceTest extends AcceptanceTestTemplate {

    /*
    User1 -- follow -> User2
    User1 -- follow -> User3
     */
    @BeforeEach
    void setUp() {
        super.init();
    }

    /**
     * User2 글 작성 -> Post 1 User1 글 조회 -> Post 1 from feed
     */
    @Test
    void givenUserHasFollowerAndCreatePost_whenFollowerUserRequestFeed_thenFollowerCanGetPostFromFeed() {
        //given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L,
            "user1이 해당 글을 조회 할 수 있음", PostPublicationState.PUBLIC);
        Long createdPostId = requestCreatePost(dto);

        //when, 팔로워 피드 요청
        List<GetPostContentResponseDto> result = requestFeed(1L);

        //then
        assertEquals(1, result.size());
        assertEquals(createdPostId, result.get(0).getId());
    }
}
