package com.likecounter.api.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "like_counter")
public class LikeCounterJpaEntity {

    @Id
    private Long id;

    @Column(name = "like_count", nullable = false)
    private long count;

    protected LikeCounterJpaEntity() {
    }

    public LikeCounterJpaEntity(Long id, long count) {
        this.id = id;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public long getCount() {
        return count;
    }
}
