package org.ivanov.andrey.diet;

i
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalorieCalculatorTest {

    @Test
    void testCalculateCaloriesForMan() {
        BigDecimal weight = new BigDecimal("70");
        Integer height = 175;
        Integer age = 25;
        Activity activity = Activity.MEDIUM;
        Sex sex = Sex.M;

        String result = String.valueOf(CalorieCalculator.calculateCalories(weight, height, age, sex, activity));
        assertEquals("2706.25", result);
    }

    @Test
    void testCalculateCaloriesForWoman() {
        BigDecimal weight = new BigDecimal("47");
        Integer height = 156;
        Integer age = 28;
        Activity activity = Activity.MINIMAL;
        Sex sex = Sex.F;

        String result = String.valueOf(CalorieCalculator.calculateCalories(weight, height, age, sex, activity));
        assertEquals("1372", result);
    }
}