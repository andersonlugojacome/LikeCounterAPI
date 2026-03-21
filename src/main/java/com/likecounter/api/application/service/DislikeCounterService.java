package com.likecounter.api.application.service;

import com.likecounter.api.domain.model.DislikeCounter;
import com.likecounter.api.domain.port.in.GetDislikeCountUseCase;
import com.likecounter.api.domain.port.in.RegisterDislikeUseCase;
import com.likecounter.api.domain.port.out.LoadDislikeCounterPort;
import com.likecounter.api.domain.port.out.SaveDislikeCounterPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DislikeCounterService implements RegisterDislikeUseCase, GetDislikeCountUseCase {

    private final LoadDislikeCounterPort loadDislikeCounterPort;
    private final SaveDislikeCounterPort saveDislikeCounterPort;

    public DislikeCounterService(LoadDislikeCounterPort loadDislikeCounterPort, SaveDislikeCounterPort saveDislikeCounterPort) {
        this.loadDislikeCounterPort = loadDislikeCounterPort;
        this.saveDislikeCounterPort = saveDislikeCounterPort;
    }

    @Override
    public long registerDislike() {
        DislikeCounter dislikeCounter = loadDislikeCounterPort.load()
                .orElseGet(DislikeCounter::initializeDefault);

        dislikeCounter.registerDislike();

        return saveDislikeCounterPort.save(dislikeCounter).getCount();
    }

    @Override
    @Transactional(readOnly = true)
    public long getCurrentCount() {
        return loadDislikeCounterPort.load()
                .orElseGet(DislikeCounter::initializeDefault)
                .getCount();
    }
}
