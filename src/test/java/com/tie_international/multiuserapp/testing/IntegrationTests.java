package com.tie_international.multiuserapp.testing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class IntegrationTests {

    @Value("${application.security.disabled:false}")
    private boolean disableSecurity;

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void requestShouldReturnEmptyList()
            throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/products", String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response.getBody());
        JsonNode productsNode = rootNode
                .path("_embedded").path("products");
        assertThat(productsNode.isArray()).isTrue();
        assertThat(productsNode.isEmpty()).isTrue();
    }
}