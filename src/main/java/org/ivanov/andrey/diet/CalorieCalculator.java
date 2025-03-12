package org.ivanov.andrey.diet;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalorieCalculator {

    public static BigDecimal calculateCalories(BigDecimal weight, Integer height, Integer age, Sex sex, Activity activity) {
        BigDecimal metabolism;

        if (sex == Sex.M) { // мужлан
            metabolism = weight.multiply(BigDecimal.valueOf(10))
                    .add(BigDecimal.valueOf(height).multiply(BigDecimal.valueOf(6.25)))
                    .subtract(BigDecimal.valueOf(age).multiply(BigDecimal.valueOf(5)))
                    .add(BigDecimal.valueOf(5)).multiply(activity.getFactor());
        } else { // баба
            metabolism = weight.multiply(BigDecimal.valueOf(10))
                    .add(BigDecimal.valueOf(height).multiply(BigDecimal.valueOf(6.25)))
                    .subtract(BigDecimal.valueOf(age).multiply(BigDecimal.valueOf(5)))
                    .subtract(BigDecimal.valueOf(161)).multiply(activity.getFactor());
        }

        //  метаболтзм округляем вниз
        return metabolism.setScale(0, RoundingMode.FLOOR);
    }
}