package org.ivanov.andrey.diet.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.ivanov.andrey.diet.domain.calories.Activity;
import org.ivanov.andrey.diet.domain.calories.Sex;

public class CaloriesRequest {

    @Min(value = 10, message = "age: must be greater than or equal to 10")
    private int age;

    @Min(value = 30, message = "weight: must be greater than or equal to 30")
    private int weight;

    @Min(value = 100, message = "height: must be greater than or equal to 100")
    private int height;

    @NotNull(message = "activity is required")
    private Activity activity;

    @NotNull(message = "sex is required")
    private Sex sex;

    // Геттеры и сеттеры
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public Activity getActivity() { return activity; }
    public void setActivity(Activity activity) { this.activity = activity; }

    public Sex getSex() { return sex; }
    public void setSex(Sex sex) { this.sex = sex; }
}