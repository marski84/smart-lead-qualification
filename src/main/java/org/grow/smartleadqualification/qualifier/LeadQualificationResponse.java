package org.grow.smartleadqualification.qualifier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LeadQualificationResponse(
        String id,
        String status,
        String model,
        List<OutputItem> output,
        Usage usage
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    record OutputItem(
            String type,
            String role,
            List<ContentPart> content
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record ContentPart(
            String type,
            String text
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Usage(
            @JsonProperty("input_tokens") int inputTokens,
            @JsonProperty("output_tokens") int outputTokens,
            @JsonProperty("total_tokens") int totalTokens
    ) {
    }


    public String answerText() {
        if (output == null) {
            throw new LeadQualificatorNoValidResponseException();
        }
        return output.stream()
                .filter(item -> "message".equals(item.type()) && item.content() != null)
                .flatMap(item -> item.content().stream())
                .filter(part -> "output_text".equals(part.type()) && part.text() != null)
                .map(part -> part.text().trim())
                .findFirst()
                .orElseThrow(LeadQualificatorNoValidResponseException::new);
    }
}
