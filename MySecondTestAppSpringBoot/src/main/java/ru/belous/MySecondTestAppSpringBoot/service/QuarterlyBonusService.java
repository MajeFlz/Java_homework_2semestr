package ru.belous.MySecondTestAppSpringBoot.service;

import ru.belous.MySecondTestAppSpringBoot.model.Positions;

public interface QuarterlyBonusService {

    double calculate(boolean isManager, Positions position, double salary, double bonus, int workDays, int year, int quarter);
}
