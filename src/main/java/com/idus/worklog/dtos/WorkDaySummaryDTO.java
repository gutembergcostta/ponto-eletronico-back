package com.idus.worklog.dtos;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.idus.worklog.utils.Utils;

public class WorkDaySummaryDTO {
    private LocalDate date;
    private List<LocalDateTime> timestamps;
    private String workedDuration;
    private String expectedDuration;
    private String remainingDuration;
    private String exceededDuration;
    private boolean isComplete;
    
    public WorkDaySummaryDTO(){
    	
    }

	public WorkDaySummaryDTO(LocalDate date, List<LocalDateTime> timestamps, Duration workedDuration,
			Duration expectedDuration, Duration remainingDuration, Duration exceededDuration, boolean isComplete) {
		super();
		this.date = date;
		this.timestamps = timestamps;
		this.workedDuration = Utils.formatDurationToHHmm(workedDuration);
		this.expectedDuration = Utils.formatDurationToHHmm(expectedDuration);
		this.remainingDuration = Utils.formatDurationToHHmm(remainingDuration);
		this.exceededDuration = Utils.formatDurationToHHmm(exceededDuration);
		this.isComplete = isComplete;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<LocalDateTime> getTimestamps() {
		return timestamps;
	}

	public void setTimestamps(List<LocalDateTime> timestamps) {
		this.timestamps = timestamps;
	}

	public String getWorkedDuration() {
		return workedDuration;
	}

	public void setWorkedDuration(String workedDuration) {
		this.workedDuration = workedDuration;
	}

	public String getExpectedDuration() {
		return expectedDuration;
	}

	public void setExpectedDuration(String expectedDuration) {
		this.expectedDuration = expectedDuration;
	}

	public String getRemainingDuration() {
		return remainingDuration;
	}

	public void setRemainingDuration(String remainingDuration) {
		this.remainingDuration = remainingDuration;
	}

	public String getExceededDuration() {
		return exceededDuration;
	}

	public void setExceededDuration(String exceededDuration) {
		this.exceededDuration = exceededDuration;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
    
    

	
}
