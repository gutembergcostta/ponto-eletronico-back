package com.idus.worklog.enums;

public enum WorkShiftType {
    SIX_HOURS("6 horas contínuas", 6, 0),
    EIGHT_HOURS("8 horas com pausa", 8, 60);
	
    private final String description;
    private final int dailyHours;
    private final int pauseMinutes;

    WorkShiftType(String description, int dailyHours, int pauseMinutes) {
        this.description = description;
        this.dailyHours = dailyHours;
        this.pauseMinutes = pauseMinutes;
    }

    public String getDescription() {
        return description;
    }

    public int getDailyHours() {
        return dailyHours;
    }

    public int getPauseMinutes() {
        return pauseMinutes;
    }
}