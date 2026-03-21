package com.likecounter.api.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dislike_counter")
public class DislikeCounterJpaEntity {

    @Id
    private Long id;

    @Column(name = "dislike_count", nullable = false)
    private long count;

    protected DislikeCounterJpaEntity() {
    }

    public DislikeCounterJpaEntity(Long id, long count) {
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
