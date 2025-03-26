package org.ivanov.andrey.diet.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.ivanov.andrey.diet.domain.calories.Activity.MINIMAL;
import static org.ivanov.andrey.diet.domain.calories.Sex.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

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

   /* @Test
    void getCaloriesFailsWithWeightValidation() {
        String url = "http://localhost:%s/calories?weight=19&height=175&age=30&activity=MINIMAL&sex=MALE".formatted(port);

        String result = restTemplate.getForObject(url, String.class);
        assertEquals("{\"kcal\":1978}", result);
    }*/


    @Test
    void getCaloriesFailsWithHeightValidation() {


    }

    @Test
    void getCaloriesFailsWithAgeValidation() {

    }
}
