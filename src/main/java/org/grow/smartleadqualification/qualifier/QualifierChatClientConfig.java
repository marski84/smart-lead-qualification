package org.grow.smartleadqualification.qualifier;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.resilience.annotation.EnableResilientMethods;

@Configuration
@EnableResilientMethods
class QualifierChatClientConfig {
    @Bean
    ChatClient qualifierChatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem(Instructions.modelInstructions)
                .build();
    }
}
