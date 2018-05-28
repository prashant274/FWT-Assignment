package com.yash.moviebookingapp.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.yash.moviebookingapp.enums.TimeSlot;

public class Screen {

	private int id;
	private String screenName;
	private Time availableTimeSlot;
	private List<Movie> allottedMovies=new ArrayList<Movie>();
	
	public Time getAvailableTimeSlot() {
		return availableTimeSlot;
	}

	public void setAvailableTimeSlot(Time availableTimeSlot) {
		this.availableTimeSlot = availableTimeSlot;
	}

	public Screen(int id, String screenName) {
		this.id = id;
		this.screenName = screenName;
		this.availableTimeSlot = Time.valueOf(TimeSlot.DEFAULT.getRequiredTime());
	}

	public List<Movie> getAllottedMovies() {
		return allottedMovies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((screenName == null) ? 0 : screenName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Screen other = (Screen) obj;
		if (screenName == null) {
			if (other.screenName != null)
				return false;
		} else if (!screenName.equals(other.screenName))
			return false;
		return true;
	}
	


}
