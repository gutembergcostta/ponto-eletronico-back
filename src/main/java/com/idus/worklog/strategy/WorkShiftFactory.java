package com.idus.worklog.strategy;

import com.idus.worklog.enums.WorkShiftType;

public class WorkShiftFactory {
    public static WorkShift getStrategy(WorkShiftType type) {
        switch (type) {
            case EIGHT_HOURS:
                return new EightHourShift();
            case SIX_HOURS:
                return new SixHourShift();
            default:
                throw new IllegalArgumentException("Tipo de jornada não suportado");
        }
    }
}
