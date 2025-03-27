package org.ivanov.andrey.diet.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CaloriesControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void getCaloriesReturnsResult() {
        String url = "http://localhost:%s/calories?weight=70&height=175&age=30&activity=MINIMAL&sex=MALE".formatted(port);

        String result = restTemplate.getForObject(url, String.class);
        assertEquals("{\"kcal\":1978}", result);
    }

    @Test
    void getCaloriesFailsWithWeightValidation() {
        String url = "http://localhost:%s/calories?weight=19&height=175&age=30&activity=MINIMAL&sex=MALE"
                .formatted(port);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("{\"errorCode\":\"INVALID_PARAMS\",\"message\":\"weight must be between 20 and 200\"}", response.getBody());
    }


    @Test
    void getCaloriesFailsWithHeightValidation() {
        String url = "http://localhost:%s/calories?weight=70&height=49&age=30&activity=MINIMAL&sex=MALE"
                .formatted(port);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("{\"errorCode\":\"INVALID_PARAMS\",\"message\":\"height must be between 130 and 230\"}", response.getBody());
    }

    @Test
    void getCaloriesFailsWithAgeValidation() {
        String url = "http://localhost:%s/calories?weight=70&height=175&age=9&activity=MINIMAL&sex=MALE"
                .formatted(port);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("{\"errorCode\":\"INVALID_PARAMS\",\"message\":\"age must be between 10 and 100\"}", response.getBody());
    }
}
