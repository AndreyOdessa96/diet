package org.ivanov.andrey.diet.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

        ResponseEntity<Map<String, String>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        Map<String, String> body = response.getBody();
        assertNotNull(body);
        assertEquals("INVALID_PARAMS", body.get("errorCode"));
        assertEquals("must be greater than or equal to 20", body.get("message"));
    }


    @Test
    void getCaloriesFailsWithHeightValidation() {
        String url = "http://localhost:%s/calories?weight=70&height=49&age=30&activity=MINIMAL&sex=MALE"
                .formatted(port);

        ResponseEntity<Map<String, String>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        Map<String, String> body = response.getBody();
        assertNotNull(body);
        assertEquals("INVALID_PARAMS", body.get("errorCode"));
        assertEquals("must be greater than or equal to 130", body.get("message"));
    }

    @Test
    void getCaloriesFailsWithAgeValidation() {
        String url = "http://localhost:%s/calories?weight=70&height=175&age=9&activity=MINIMAL&sex=MALE"
                .formatted(port);

        ResponseEntity<Map<String, String>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        Map<String, String> body = response.getBody();
        System.out.println("BODY = " + body);
        assertNotNull(body);
        assertEquals("INVALID_PARAMS", body.get("errorCode"));
        assertEquals("must be greater than or equal to 10", body.get("message"));
    }
}
