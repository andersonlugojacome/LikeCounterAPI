package com.likecounter.api.domain.port.out;

import com.likecounter.api.domain.model.LikeCounter;

import java.util.Optional;

public interface LoadLikeCounterPort {

    Optional<LikeCounter> load();
}
