package org.grow.smartleadqualification.messages;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/message")
class MessageCommandController {

    private final MessageService messageService;

    MessageCommandController(final MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping()
    ResponseEntity<Void> registerMessage(@Valid @RequestBody InboundMessageDto inboundMessageDto) {
        Message message = messageService.storeMessage(inboundMessageDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(message.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }


}
