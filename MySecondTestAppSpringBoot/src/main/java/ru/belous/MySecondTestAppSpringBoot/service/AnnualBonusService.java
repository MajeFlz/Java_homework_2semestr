package ru.belous.MySecondTestAppSpringBoot.service;

import ru.belous.MySecondTestAppSpringBoot.model.Positions;

public interface AnnualBonusService {

    double calculate(Positions position, double salary, double bonus, int workDays, int year);

}
