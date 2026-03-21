package com.likecounter.api.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DislikeCounterTest {

    @Test
    void shouldThrowExceptionForNegativeCount() {
        assertThatThrownBy(() -> new DislikeCounter(1L, -1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Dislike count cannot be negative");
    }

    @Test
    void shouldAcceptZeroCount() {
        DislikeCounter counter = new DislikeCounter(1L, 0L);
        assertThat(counter.getCount()).isEqualTo(0L);
    }

    @Test
    void shouldIncrementCount() {
        DislikeCounter counter = new DislikeCounter(1L, 5L);
        counter.registerDislike();
        assertThat(counter.getCount()).isEqualTo(6L);
    }
}
