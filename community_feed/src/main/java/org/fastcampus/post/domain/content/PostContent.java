package org.fastcampus.post.domain.content;

public class PostContent extends Content {

    private static final int MIN_POST_LENGTH = 5;
    private static final int MAX_POST_LENGTH = 500;

    public PostContent(String content) {
        super(content);
    }

    @Override
    protected void checkText(String context) {
        if (context == null || context.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (context.length() < MIN_POST_LENGTH) {
            throw new IllegalArgumentException();
        }

        if (context.length() > MAX_POST_LENGTH) {
            throw new IllegalArgumentException();
        }
    }
}
