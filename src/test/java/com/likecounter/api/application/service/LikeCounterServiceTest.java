package com.likecounter.api.application.service;

import com.likecounter.api.domain.model.LikeCounter;
import com.likecounter.api.domain.port.out.LoadLikeCounterPort;
import com.likecounter.api.domain.port.out.SaveLikeCounterPort;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LikeCounterServiceTest {

    @Test
    void shouldRegisterLikeAndReturnUpdatedCount() {
        LoadLikeCounterPort loadPort = mock(LoadLikeCounterPort.class);
        SaveLikeCounterPort savePort = mock(SaveLikeCounterPort.class);
        LikeCounterService service = new LikeCounterService(loadPort, savePort);

        when(loadPort.load()).thenReturn(Optional.of(new LikeCounter(1L, 5L)));
        when(savePort.save(org.mockito.ArgumentMatchers.any(LikeCounter.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        long updatedCount = service.registerLike();

        assertThat(updatedCount).isEqualTo(6L);
    }
}
