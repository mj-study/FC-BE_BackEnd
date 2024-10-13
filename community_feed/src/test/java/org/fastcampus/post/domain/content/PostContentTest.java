package org.fastcampus.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent() {
        //given
        String text = "this is a test";

        //when
        PostContent content = new PostContent(text);

        //then
        assertEquals(text, content.contentText);
    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenThrowError() {
        //given
        String content = "a".repeat(501);

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"가", "나", "다", "김"})
    void givenContentLengthIsOverAndKorean_whenCreated_thenThrowError(String koreanWord) {
        //given
//        String content = "가".repeat(501);
        String content = koreanWord.repeat(501);

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "B", "가", "나"})
    void givenContentLengthIsUnder_whenCreated_thenReturnTextContent(String word) {
        //given
        String content = word.repeat(4);

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmpty_whenCreated_thenReturnTextContent(String value) {
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
    }


    @Test
    void whenUpdateContentLengthIsOver_thenThrowError() {
        //given
        String initialContent = "this is a test";
        PostContent content = new PostContent(initialContent);
        String newContent = "a".repeat(501);

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(newContent));
    }


    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "abc", "abcd"})
    void whenUpdateContentLengthIsUnder_thenThrowError(String word) {
        //given
        String initialContent = "this is a test";
        PostContent content = new PostContent(initialContent);

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(word));
    }
}
