package ru.belous.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.belous.MySecondTestAppSpringBoot.model.Positions;
import java.util.Calendar;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

    @Override
    public double calculate(Positions position, double salary, double bonus, int workDays, int year) {
        int daysInYear = getCountDaysInYear(year);
        return salary * bonus * daysInYear * position.getPositionCoefficient() / workDays;
    }

    private int getCountDaysInYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        return calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
    }
}