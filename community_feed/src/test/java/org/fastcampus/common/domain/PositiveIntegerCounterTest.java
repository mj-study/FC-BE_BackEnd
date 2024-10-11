package org.fastcampus.common.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositiveIntegerCounterTest {

    @Test
    @DisplayName("카운트 증가")
    void givenCreated_whenIncrease_thenCountIsOne() {
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // when
        counter.increase();

        // then
        assertEquals(1, counter.getCount());
    }

    @Test
    @DisplayName("카운트 감소")
    void givenCreatedAndIncreased_whenDecrease_thenCountZero() {
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        counter.increase();

        //when
        counter.decrease();

        //then
        assertEquals(0, counter.getCount());
    }

    @Test
    void givenCreated_whenDecrease_thenCountZero() {
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        //when
        counter.decrease();

        //then
        assertEquals(0, counter.getCount());
    }
}