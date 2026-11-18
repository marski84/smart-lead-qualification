package org.grow.smartleadqualification.messages;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class MessageCommandServiceImpl implements MessageCommandService {

    private final MessageRepository messageRepository;

    MessageCommandServiceImpl(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Long storeMessage(InboundMessageDto inboundMessageDto) {
        Message message = MessageConverter.fromDto(inboundMessageDto);
        return messageRepository.save(message).getId();
    }
}
