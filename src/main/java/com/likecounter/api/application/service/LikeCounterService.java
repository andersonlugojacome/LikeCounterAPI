package com.likecounter.api.application.service;

import com.likecounter.api.domain.model.LikeCounter;
import com.likecounter.api.domain.port.in.GetLikeCountUseCase;
import com.likecounter.api.domain.port.in.RegisterLikeUseCase;
import com.likecounter.api.domain.port.out.LoadLikeCounterPort;
import com.likecounter.api.domain.port.out.SaveLikeCounterPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LikeCounterService implements RegisterLikeUseCase, GetLikeCountUseCase {

    private final LoadLikeCounterPort loadLikeCounterPort;
    private final SaveLikeCounterPort saveLikeCounterPort;

    public LikeCounterService(LoadLikeCounterPort loadLikeCounterPort, SaveLikeCounterPort saveLikeCounterPort) {
        this.loadLikeCounterPort = loadLikeCounterPort;
        this.saveLikeCounterPort = saveLikeCounterPort;
    }

    @Override
    public long registerLike() {
        LikeCounter likeCounter = loadLikeCounterPort.load()
                .orElseGet(LikeCounter::initializeDefault);

        likeCounter.registerLike();

        return saveLikeCounterPort.save(likeCounter).getCount();
    }

    @Override
    @Transactional(readOnly = true)
    public long getCurrentCount() {
        return loadLikeCounterPort.load()
                .orElseGet(LikeCounter::initializeDefault)
                .getCount();
    }
}
