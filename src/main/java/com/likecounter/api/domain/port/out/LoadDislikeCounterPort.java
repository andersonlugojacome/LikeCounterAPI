package com.likecounter.api.domain.port.out;

import com.likecounter.api.domain.model.DislikeCounter;

import java.util.Optional;

public interface LoadDislikeCounterPort {

    Optional<DislikeCounter> load();
}
