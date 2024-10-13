package org.fastcampus.post.domain.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CommentContentTest {

    @Test
    void testCommentContentValidity() {
        // given
        String expectedContent = "This is a comment";
        CommentContent commentContent = new CommentContent(expectedContent);

        // when
        String actualContent = commentContent.getContentText();

        // then
        assertEquals(expectedContent, actualContent,
            "The comment content should match the expected content");
    }


    @ParameterizedTest
    @ValueSource(strings = {"a", "가"})
    void testCommentContentTooLongParameterized(String word) {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(word.repeat(101)));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testCommentContentEmptyParameterized(String value) {
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(value));
    }


}