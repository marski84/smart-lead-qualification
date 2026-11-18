package org.grow.smartleadqualification.messages;

import org.grow.smartleadqualification.messages.exception.MessageNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
class MessageQueryServiceImpl implements MessageQueryService {

    private final MessageRepository messageRepository;

    MessageQueryServiceImpl(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageStatusReportDto findById(Long id) {
        return messageRepository.findById(id)
                .map(MessageConverter::toReportDto)
                .orElseThrow(() -> new MessageNotFoundException(id));
    }

    @Override
    public Page<MessageStatusReportDto> getAll(Pageable pageable) {
        return messageRepository.findAll(pageable).map(MessageConverter::toReportDto);
    }
}
