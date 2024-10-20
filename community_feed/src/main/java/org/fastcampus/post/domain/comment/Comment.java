package org.fastcampus.post.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.user.domain.User;

@Getter
@Builder
@AllArgsConstructor
public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final CommentContent content;
    private final PositiveIntegerCounter likeCounter;

    public static Comment createComment(Post post, User author, String content) {
        return new Comment(null, post, author, new CommentContent(content));
    }

    public Comment(Long id, Post post, User author, CommentContent content) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        if (post == null) {
            throw new IllegalArgumentException();
        }

        if (content == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCounter = new PositiveIntegerCounter();
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        likeCounter.increase();
    }

    public void unLike(User user) {
        this.likeCounter.decrease();
    }

    public void updateComment(User user, String updateContent) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.content.updateContent(updateContent);
    }

    public int getLikeCount() {
        return likeCounter.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }

    public CommentContent getContentObject() {
        return content;
    }
}
