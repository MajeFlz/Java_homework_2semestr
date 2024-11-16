package ru.belous.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.belous.MySecondTestAppSpringBoot.model.Positions;

import java.time.Year;

@Service
public class QuarterlyBonusServiceImpl implements QuarterlyBonusService {

    @Override
    public double calculate(boolean isManager, Positions position, double salary, double bonus, int workDays, int year, int quarter) {
        if (!isManager) {
            throw new IllegalArgumentException("Пользователь не является менеджером");
        }
        int daysInYearQuarter = getCountDaysInYearQuarter(year, quarter);
        return salary * bonus * daysInYearQuarter * position.getPositionCoefficient() / workDays;
    }

    private int getCountDaysInYearQuarter(int year, int quarter) {
        if (quarter < 1 || quarter > 4) {
            throw new IllegalArgumentException("Номер квартала должен быть от 1 до 4");
        }
        int answer = 0;
        int startMonth = (quarter - 1) * 3 + 1;
        int endMonth = startMonth + 2;
        for (int month = startMonth; month <= endMonth; month++) {
            answer += Year.of(year).atMonth(month).lengthOfMonth();
        }
        return answer;
    }
}