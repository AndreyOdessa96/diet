package org.ivanov.andrey.diet.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.ivanov.andrey.diet.domain.calories.Activity;
import org.ivanov.andrey.diet.domain.calories.CalorieCalculator;
import org.ivanov.andrey.diet.domain.calories.Sex;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.math.BigDecimal;

@RestController
@RequestMapping("/calories")
@Validated
public class CaloriesController {

    private final CalorieCalculator calorieCalculator;

    @Inject
    public CaloriesController(CalorieCalculator calorieCalculator) {
        this.calorieCalculator = calorieCalculator;
    }

    @GetMapping
    public CaloriesResponse getCalories(
            @RequestParam @Min(20) @Max(200) BigDecimal weight,
            @RequestParam @Min(130) @Max(230) Integer height,
            @RequestParam @Min(10) @Max(100) Integer age,
            @RequestParam Activity activity,
            @RequestParam Sex sex) {

        BigDecimal calories = calorieCalculator.calculateCalories(weight, height, age, sex, activity);
        return new CaloriesResponse(calories);
    }
}