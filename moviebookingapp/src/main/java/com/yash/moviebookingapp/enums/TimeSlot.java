package com.yash.moviebookingapp.enums;

public enum TimeSlot {
	
	DEFAULT("12:00:00"),HOUSEKEEPING("01:00:00");
	private String requiredTime;
	private TimeSlot (String requiredTime) {
		this.requiredTime=requiredTime;
	}
	public String getRequiredTime() {
		return requiredTime;
	}

}
