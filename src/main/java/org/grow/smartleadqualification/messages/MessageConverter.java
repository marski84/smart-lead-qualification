package org.grow.smartleadqualification.messages;

import java.time.Instant;

class MessageConverter {
    public static Message fromDto(InboundMessageDto inboundMessageDto) {
        return Message.builder()
                .text(inboundMessageDto.message())
                .receivedAt(Instant.now())
                .status(MessageStatus.PENDING)
                .processedDate(null)
                .build();
    }

    public static OutboundMessageDto toDto(Message message) {
        return new OutboundMessageDto(message.getId(), message.getText(), message.getReceivedAt());
    }

    public static MessageStatusReportDto toReportDto(Message message) {
        return new MessageStatusReportDto(message.getId(), message.getText(), message.getReceivedAt(), message.getStatus(), message.getProcessedDate());
    }
}
