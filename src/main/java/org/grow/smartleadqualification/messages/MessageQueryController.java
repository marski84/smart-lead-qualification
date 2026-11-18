package org.grow.smartleadqualification.messages;

import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/message/report")
@Validated
class MessageQueryController {

    private final MessageQueryService messageQueryService;

    MessageQueryController(final MessageQueryService messageQueryService) {
        this.messageQueryService = messageQueryService;
    }

    @GetMapping("/{id}")
    ResponseEntity<MessageStatusReportDto> getMessageStatusById(@PathVariable @Positive(message = "Message id must be greater than 0") Long id) {
        return ResponseEntity.ok(messageQueryService.findById(id));
    }

    @GetMapping("/all")
    ResponseEntity<PagedModel<MessageStatusReportDto>> getAll(
            @PageableDefault(size = 10, sort = {"receivedAt", "id"}, direction = Sort.Direction.DESC)
            Pageable pageable) {
        Page<MessageStatusReportDto> resultPage = messageQueryService.getAll(pageable);
        return ResponseEntity.ok(new PagedModel<>(resultPage));
    }
}
