package org.fastcampus.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;

@Getter
@Builder
@AllArgsConstructor
public class Post {

    private final Long id;
    private final User author;
    private final PostContent content;
    private final PositiveIntegerCounter likeCounter;
    private PostPublicationState state;

    public static Post createPost(Long id, User author, String content,
        PostPublicationState state) {
        return new Post(id, author, new PostContent(content), state);
    }

    public static Post createDefaultPost(Long id, User author, String content) {
        return new Post(id, author, new PostContent(content), PostPublicationState.PUBLIC);
    }

    public Post(Long id, User author, PostContent content) {
        this(id, author, content, PostPublicationState.PUBLIC);
    }

    public Post(Long id, User author, PostContent content, PostPublicationState state) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCounter = new PositiveIntegerCounter();
        this.state = state;
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        likeCounter.increase();
    }

    public void unLike() {
        this.likeCounter.decrease();
    }

    public void updatePost(User user, String updateContent, PostPublicationState state) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.state = state;
        this.content.updateContent(updateContent);
    }

    public int getLikeCount() {
        return likeCounter.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }

    public PostContent getContentObject() {
        return content;
    }
}
