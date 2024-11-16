package ru.belous.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.belous.MySecondTestAppSpringBoot.model.Positions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class QuarterlyBonusServiceImplTest {

    @Test
    void calculate() {
        //given
        Positions position = Positions.PM;
        double bonus = 2.8;
        int workDays = 45;
        double salary = 200000.00;

        // when
        double result = new QuarterlyBonusServiceImpl().calculate(true, position, salary, bonus, workDays, 2021, 1);

        // then
        double expected = 3360000.0;
        assertThat(result).isEqualTo(expected);
    }
}