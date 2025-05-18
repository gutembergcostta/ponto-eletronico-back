package com.idus.worklog.strategy;

import jakarta.persistence.DiscriminatorValue;


@DiscriminatorValue("SIX_HOURS")
public class SixHourShift extends WorkShift {

    public SixHourShift() {
        this.description = "6 horas contínuas";
        this.dailyHours = 6;
        this.pauseMinutes = 0;
    }
}
