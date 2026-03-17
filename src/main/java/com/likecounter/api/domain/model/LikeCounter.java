package com.likecounter.api.domain.model;

import java.util.Objects;

public class LikeCounter {

    private final Long id;
    private long count;

    public LikeCounter(Long id, long count) {
        if (count < 0) {
            throw new IllegalArgumentException("Like count cannot be negative");
        }
        this.id = id;
        this.count = count;
    }

    public static LikeCounter initializeDefault() {
        return new LikeCounter(1L, 0L);
    }

    public void registerLike() {
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
        if (!(object instanceof LikeCounter that)) {
            return false;
        }
        return count == that.count && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count);
    }
}
