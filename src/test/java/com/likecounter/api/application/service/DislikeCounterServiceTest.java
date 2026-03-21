package com.likecounter.api.application.service;

import com.likecounter.api.domain.model.DislikeCounter;
import com.likecounter.api.domain.port.out.LoadDislikeCounterPort;
import com.likecounter.api.domain.port.out.SaveDislikeCounterPort;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DislikeCounterServiceTest {

    @Test
    void shouldRegisterDislikeAndReturnUpdatedCount() {
        LoadDislikeCounterPort loadPort = mock(LoadDislikeCounterPort.class);
        SaveDislikeCounterPort savePort = mock(SaveDislikeCounterPort.class);
        DislikeCounterService service = new DislikeCounterService(loadPort, savePort);

        when(loadPort.load()).thenReturn(Optional.of(new DislikeCounter(1L, 5L)));
        when(savePort.save(org.mockito.ArgumentMatchers.any(DislikeCounter.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        long updatedCount = service.registerDislike();

        assertThat(updatedCount).isEqualTo(6L);
    }

    @Test
    void shouldReturnZeroWhenCounterDoesNotExist() {
        LoadDislikeCounterPort loadPort = mock(LoadDislikeCounterPort.class);
        SaveDislikeCounterPort savePort = mock(SaveDislikeCounterPort.class);
        DislikeCounterService service = new DislikeCounterService(loadPort, savePort);

        when(loadPort.load()).thenReturn(Optional.empty());

        long currentCount = service.getCurrentCount();

        assertThat(currentCount).isEqualTo(0L);
    }

    @Test
    void shouldCreateNewCounterAndIncrementOnFirstDislike() {
        LoadDislikeCounterPort loadPort = mock(LoadDislikeCounterPort.class);
        SaveDislikeCounterPort savePort = mock(SaveDislikeCounterPort.class);
        DislikeCounterService service = new DislikeCounterService(loadPort, savePort);

        when(loadPort.load()).thenReturn(Optional.empty());
        when(savePort.save(org.mockito.ArgumentMatchers.any(DislikeCounter.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        long updatedCount = service.registerDislike();

        assertThat(updatedCount).isEqualTo(1L);
    }

    @Test
    void shouldReturnCurrentCountFromExistingCounter() {
        LoadDislikeCounterPort loadPort = mock(LoadDislikeCounterPort.class);
        SaveDislikeCounterPort savePort = mock(SaveDislikeCounterPort.class);
        DislikeCounterService service = new DislikeCounterService(loadPort, savePort);

        when(loadPort.load()).thenReturn(Optional.of(new DislikeCounter(1L, 10L)));

        long currentCount = service.getCurrentCount();

        assertThat(currentCount).isEqualTo(10L);
    }
}
