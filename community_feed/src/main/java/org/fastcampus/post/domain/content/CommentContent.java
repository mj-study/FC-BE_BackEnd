package org.fastcampus.post.domain.content;

public class CommentContent extends Content {

    private static final int MAX_COMMENT_LENGTH = 100;

    public CommentContent(String contentText) {
        super(contentText);
    }

    @Override
    protected void checkText(String contextText) {
        if (contextText == null || contextText.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (contextText.length() > MAX_COMMENT_LENGTH) {
            throw new IllegalArgumentException();
        }
    }
}
