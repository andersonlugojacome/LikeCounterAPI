package com.likecounter.api.domain.port.out;

import com.likecounter.api.domain.model.DislikeCounter;

public interface SaveDislikeCounterPort {

    DislikeCounter save(DislikeCounter dislikeCounter);
}
