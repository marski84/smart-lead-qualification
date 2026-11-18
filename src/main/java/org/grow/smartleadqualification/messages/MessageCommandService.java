package org.grow.smartleadqualification.messages;

interface MessageCommandService {

    /**
     * Zapisuje nową wiadomość ze statusem PENDING.
     *
     * @return id zapisanej wiadomości
     */
    Long storeMessage(InboundMessageDto inboundMessageDto);
}
