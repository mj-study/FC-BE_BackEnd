package org.fastcampus.post.application;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.post.application.dto.CreateCommentRequestDto;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.application.dto.UpdatePostRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.domain.User;

public class PostApplicationTestTemplate {

    final UserService userService = FakeObjectFactory.getUserService();
    final PostService postService = FakeObjectFactory.getPostService();
    final CommentService commentService = FakeObjectFactory.getCommentService();

    final User user = userService.createUser(new CreateUserRequestDto("user1", null));
    final User otherUser = userService.createUser(new CreateUserRequestDto("user2", null));

    final CreatePostRequestDto postRequestDto = new CreatePostRequestDto(user.getId(), "테스트 post",
        PostPublicationState.PUBLIC);
    final UpdatePostRequestDto postUpdateDto = new UpdatePostRequestDto(1L, user.getId(),
        "수정합니다요",
        PostPublicationState.PRIVATE);
    final Post post = postService.createPost(postRequestDto);

    final String commentContent = "comment test";
    final CreateCommentRequestDto commentRequestDto = new CreateCommentRequestDto(post.getId(),
        user.getId(), commentContent);
    final Comment comment = commentService.createComment(commentRequestDto);
}
