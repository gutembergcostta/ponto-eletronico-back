package com.idus.worklog.strategy;

import jakarta.persistence.DiscriminatorValue;
import java.time.Duration;
import java.time.LocalDateTime;


@DiscriminatorValue("EIGHT_HOURS")
public class EightHourShift extends WorkShift {

    public EightHourShift() {
        this.description = "8 horas com pausa";
        this.dailyHours = 8;
        this.pauseMinutes = 60;
    }
    
    @Override
    public Duration calculateTotalWorked(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Duration total = Duration.between(startDateTime, endDateTime);

        Duration pauseDuration = Duration.ofMinutes(pauseMinutes);
        if (total.compareTo(pauseDuration) > 0) {
            return total.minus(Duration.ofMinutes(pauseMinutes));
        }

        return Duration.ZERO;
    }
    
}
