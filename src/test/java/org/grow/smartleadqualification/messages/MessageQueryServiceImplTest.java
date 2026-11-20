package org.grow.smartleadqualification.messages;

import org.grow.smartleadqualification.messages.exception.MessageNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageQueryServiceImplTest {

    private static final Instant RECEIVED_AT = Instant.parse("2026-11-20T10:00:00Z");
    private static final Instant PROCESSED_AT = Instant.parse("2026-11-20T10:05:00Z");

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageQueryServiceImpl underTest;

    @Test
    void findById_existingId_returnsMappedReportDto() {
        // given
        Long id = 1L;
        when(messageRepository.findById(id)).thenReturn(Optional.of(processedMessage(id)));

        // when
        MessageStatusReportDto result = underTest.findById(id);

        // then
        assertThat(result).isEqualTo(
                new MessageStatusReportDto(id, "test" + id, RECEIVED_AT, MessageStatus.PROCESSED, PROCESSED_AT));
    }

    @Test
    void findById_missingId_throwsMessageNotFoundException() {
        // given
        Long id = 1L;
        when(messageRepository.findById(id)).thenReturn(Optional.empty());

        // when / then
        assertThatThrownBy(() -> underTest.findById(id))
                .isInstanceOf(MessageNotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void getAll_returnsMappedItemsAndKeepsPageMetadata() {
        // given
        Pageable pageable = PageRequest.of(0, 2);
        long totalInDatabase = 5;
        Page<Message> repositoryPage = new PageImpl<>(
                List.of(processedMessage(1L), processedMessage(2L)), pageable, totalInDatabase);
        when(messageRepository.findAll(pageable)).thenReturn(repositoryPage);

        // when
        Page<MessageStatusReportDto> result = underTest.getAll(pageable);

        // then
        assertThat(result.getContent()).containsExactly(
                new MessageStatusReportDto(1L, "test1", RECEIVED_AT, MessageStatus.PROCESSED, PROCESSED_AT),
                new MessageStatusReportDto(2L, "test2", RECEIVED_AT, MessageStatus.PROCESSED, PROCESSED_AT));
        assertThat(result.getTotalElements()).isEqualTo(totalInDatabase);
        assertThat(result.getTotalPages()).isEqualTo(3);
        assertThat(result.getNumber()).isZero();
        assertThat(result.getSize()).isEqualTo(2);
    }

    @Test
    void getAll_emptyRepository_returnsEmptyPage() {
        // given
        Pageable pageable = PageRequest.of(0, 10);
        when(messageRepository.findAll(pageable)).thenReturn(Page.empty(pageable));

        // when
        Page<MessageStatusReportDto> result = underTest.getAll(pageable);

        // then
        assertThat(result.getContent()).isEmpty();
        assertThat(result.getTotalElements()).isZero();
    }

    private Message processedMessage(Long id) {
        return Message.builder()
                .id(id)
                .text("test" + id)
                .receivedAt(RECEIVED_AT)
                .status(MessageStatus.PROCESSED)
                .processedDate(PROCESSED_AT)
                .build();
    }
}
