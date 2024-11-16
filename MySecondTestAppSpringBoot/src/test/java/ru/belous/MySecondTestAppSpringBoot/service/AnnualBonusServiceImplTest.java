package ru.belous.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.belous.MySecondTestAppSpringBoot.model.Positions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {
        //given
        Positions position = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        // when
        double result = new AnnualBonusServiceImpl().calculate(position, salary, bonus, workDays, 2021);

        // then
        double expected = 360493.8271604938;
        assertThat(result).isEqualTo(expected);
    }
}