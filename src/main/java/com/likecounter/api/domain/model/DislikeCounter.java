package com.likecounter.api.domain.model;

import java.util.Objects;

public class DislikeCounter {

    private final Long id;
    private long count;

    public DislikeCounter(Long id, long count) {
        if (count < 0) {
            throw new IllegalArgumentException("Dislike count cannot be negative");
        }
        this.id = id;
        this.count = count;
    }

    public static DislikeCounter initializeDefault() {
        return new DislikeCounter(1L, 0L);
    }

    public void registerDislike() {
        count++;
    }

    public Long getId() {
        return id;
    }

    public long getCount() {
        return count;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof DislikeCounter that)) {
            return false;
        }
        return count == that.count && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count);
    }
}
