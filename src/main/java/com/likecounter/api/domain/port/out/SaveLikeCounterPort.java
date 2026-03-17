package com.likecounter.api.domain.port.out;

import com.likecounter.api.domain.model.LikeCounter;

public interface SaveLikeCounterPort {

    LikeCounter save(LikeCounter likeCounter);
}
