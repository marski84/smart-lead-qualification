package org.grow.smartleadqualification.messages;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class MessageCommandControllerTest {

    private final String TEST_URI = "/api/v1/message";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registerMessage_shouldSucceed() throws Exception {
        mockMvc.perform(post(TEST_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new InboundMessageDto("test message"))
                        )
                )
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "http://localhost/api/v1/message/1"));
    }

    @Test
    void registerMessage_blankMessage_returnsValidationError() throws Exception {
        mockMvc.perform(post(TEST_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new InboundMessageDto(""))))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.path").value(TEST_URI))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errorDescription").value("Validation errors"))
                .andExpect(jsonPath("$.errors", hasSize(2)))
                .andExpect(jsonPath("$.errors", containsInAnyOrder(
                        "Message cannot be empty",
                        "Message cannot be blank")));
    }

    @Test
    void registerMessage_missingMessageField_returnsValidationError() throws Exception {
        mockMvc.perform(post(TEST_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errorDescription").value("Validation errors"))
                .andExpect(jsonPath("$.errors", not(empty())));
    }

    @Test
    void registerMessage_noBody_returnsMalformedBodyError() throws Exception {
        mockMvc.perform(post(TEST_URI).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.path").value(TEST_URI))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errorDescription").value("Malformed request body"))
                .andExpect(jsonPath("$.errors", hasSize(1)));
    }

    @Test
    void registerMessage_invalidJson_returnsMalformedBodyError() throws Exception {
        mockMvc.perform(post(TEST_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"message\": "))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errorDescription").value("Malformed request body"));
    }
}