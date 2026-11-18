package org.grow.smartleadqualification.messages;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface MessageQueryService {

    /**
     * @throws org.grow.smartleadqualification.messages.exception.MessageNotFoundException gdy wiadomość nie istnieje
     */
    MessageStatusReportDto findById(Long id);

    Page<MessageStatusReportDto> getAll(Pageable pageable);
}
