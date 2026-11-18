package org.grow.smartleadqualification.messages;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "messages")
class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 4000)
    private String text;

    private Instant receivedAt;

    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    private Instant processedDate;

    public void markProcessed(Instant date) {
        setStatus(MessageStatus.PROCESSED);
        setProcessedDate(date);
    }

    public void markProcessedFailed(Instant date) {
        setStatus(MessageStatus.FAILED);
        setProcessedDate(date);
    }

    public void markProcessing(Instant date) {
        setStatus(MessageStatus.PROCESSING);
        setProcessedDate(date);
    }


    private void setText(final String text) {
        this.text = text;
    }

    private void setReceivedAt(final Instant receivedAt) {
        this.receivedAt = receivedAt;
    }

    private void setStatus(final MessageStatus status) {
        this.status = status;
    }

    private void setProcessedDate(final Instant processedDate) {
        this.processedDate = processedDate;
    }
}
