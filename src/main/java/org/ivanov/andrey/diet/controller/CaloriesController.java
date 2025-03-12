package org.ivanov.andrey.diet.controller;

import org.ivanov.andrey.diet.CalorieCalculator;
import org.springframework.web.bind.annotation.*;
import org.ivanov.andrey.diet.Activity;
import org.ivanov.andrey.diet.Sex;
import java.math.BigDecimal;

@RestController
@RequestMapping("/calories")
public class CaloriesController {

    @GetMapping
    public String getCalories(
            @RequestParam BigDecimal weight,
            @RequestParam Integer height,
            @RequestParam Integer age,
            @RequestParam Activity activity,
            @RequestParam Sex sex) {

        BigDecimal calories = CalorieCalculator.calculateCalories(weight, height, age, sex, activity);
        return calories.toPlainString() + " kcal";
    }
    }
