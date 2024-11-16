package ru.belous.MySecondTestAppSpringBoot.model;

import lombok.Getter;

@Getter
public enum Positions {

    DEV(2.2),
    HR(1.2),
    TL(2.6),
    QA(1.8),
    PM(3.0),
    BA(2.4);

    private final double positionCoefficient;

    Positions(double positionCoefficient) {
        this.positionCoefficient = positionCoefficient;
    }
}
