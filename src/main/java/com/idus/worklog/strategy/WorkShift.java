package com.idus.worklog.strategy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public abstract class WorkShift {
	protected Long id;
	protected String description;
	protected Integer dailyHours;
	protected Integer pauseMinutes;
    
	public Duration calculateTotalWorked(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		return Duration.between(startDateTime, endDateTime);
	}
	
	public Duration sumWorkIntervals(List<LocalDateTime> points) {
        Duration total = Duration.ZERO;

        for (int i = 0; i < points.size() - 1; i += 2) {
            LocalDateTime start = points.get(i);
            LocalDateTime end = points.get(i + 1);
            total = total.plus(Duration.between(start, end));
        }

        return total;
    }
	
	public Duration expectedDuration() {
        return Duration.ofHours(dailyHours);
    }
	
	public Duration exceedHoursPerDay(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Duration worked = calculateTotalWorked(startDateTime, endDateTime);
        Duration expected = expectedDuration();
        if(worked.compareTo(expected) > 0){
            return worked.minus(expected);
        }
        return Duration.ZERO;
    }
    
	public String getDescription() {
    	return this.description;
    }
}
