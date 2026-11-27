package org.grow.smartleadqualification.qualifier;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@AllArgsConstructor
@Slf4j
public class QualifierFacade {
    private final ChatClient qualifierChatClient;

    private final Pattern VALID_ANSWER = Pattern.compile("^(Not a lead|Lead(;[^;]+){3};.+)$");

    @Retryable(
            includes = LeadQualificatorNoValidResponseException.class,
            maxRetries = 2,
            delay = 500,
            multiplier = 2
    )
    public String qualify(String inboundMessage) {
        String answer = qualifierChatClient.prompt()
                .user(inboundMessage)
                .call()
                .content();

        String trimmed = answer == null ? "" : answer.trim();

        if (!VALID_ANSWER.matcher(trimmed).matches()) {
            throw new LeadQualificatorNoValidResponseException();
        }
        log.debug("Qualifier answer: {}", answer);
        return trimmed;
    }
}
