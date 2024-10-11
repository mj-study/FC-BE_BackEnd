package org.fastcampus.post.domain.comment;

import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final CommentContent content;
    private final PositiveIntegerCounter likeCounter;

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
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.content.updateContent(updateContent);
    }
}
