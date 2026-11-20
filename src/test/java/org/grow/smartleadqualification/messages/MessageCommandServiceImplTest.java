package org.grow.smartleadqualification.messages;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageCommandServiceImplTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageCommandServiceImpl underTest;

    @Test
    void storeMessage_savesPendingMessageAndReturnsGeneratedId() {
        // given
        Long generatedId = 42L;
        when(messageRepository.save(any(Message.class))).thenAnswer(invocation -> {
            Message toSave = invocation.getArgument(0);
            ReflectionTestUtils.setField(toSave, "id", generatedId);
            return toSave;
        });

        // when
        Long result = underTest.storeMessage(new InboundMessageDto("test message"));

        // then
        assertThat(result).isEqualTo(generatedId);
        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
        verify(messageRepository).save(captor.capture());
        Message saved = captor.getValue();
        assertThat(saved.getText()).isEqualTo("test message");
        assertThat(saved.getStatus()).isEqualTo(MessageStatus.PENDING);
        assertThat(saved.getReceivedAt()).isNotNull();
        assertThat(saved.getProcessedDate()).isNull();
    }
}
