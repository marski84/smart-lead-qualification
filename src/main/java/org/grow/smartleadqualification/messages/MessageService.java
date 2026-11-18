package org.grow.smartleadqualification.messages;

import org.grow.smartleadqualification.messages.exception.MessageNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
class MessageService {

    private final MessageRepository messageRepository;

    MessageService(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message storeMessage(InboundMessageDto inboundMessageDto) {
        Message message = MessageConverter.fromDto(inboundMessageDto);
        return messageRepository.save(message);
    }

    public Message findById(Long id) {
        return messageRepository.findById(id).orElseThrow(
                () -> new MessageNotFoundException(id)
        );
    }

    public Page<Message> getAll(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }
}
