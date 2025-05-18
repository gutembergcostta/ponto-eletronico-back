package com.idus.worklog.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class Utils {
	private static final long ALLOWED_DELAY_SECONDS = 15;

	public static boolean isDelayed(LocalDateTime expectedTime, LocalDateTime actualTime) {
		Duration duration = Duration.between(expectedTime, actualTime);
		return duration.getSeconds() > ALLOWED_DELAY_SECONDS;
	}
	
	public static String formatDurationToHHmm(Duration duration) {
        long totalMinutes = duration.toMinutes();
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
}
