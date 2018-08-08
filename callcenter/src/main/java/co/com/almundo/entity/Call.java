package co.com.almundo.entity;

import java.util.Random;

public class Call {
	public static final int MIN_DURATION = 5;
	public static final int MAX_DURATION = 10;
	private int duration;
	private String description;

	public Call(String description) {
		duration = new Random().nextInt(MIN_DURATION + 1) + MIN_DURATION;
		this.description = description;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
