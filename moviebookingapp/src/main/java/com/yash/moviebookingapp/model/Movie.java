package com.yash.moviebookingapp.model;

import java.sql.Time;
import java.util.List;

public class Movie {

	private String title;

	private String production;

	private List<String> actors;

	private Time duration;

	private boolean allocated;

	public Movie(String title, String production, List<String> actors, Time duration) {
		super();
		this.title = title;
		this.production = production;
		this.actors = actors;
		this.duration = duration;
		allocated = true;
	}

	public String getTitle() {
		return title;
	}

	public String getProduction() {
		return production;
	}

	public List<String> getActors() {
		return actors;
	}

	public Time getDuration() {
		return duration;
	}

	public boolean isAllocated() {
		return allocated;
	}
	
	public void setAllocated(boolean allocated) {
		this.allocated = allocated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (allocated ? 1231 : 1237);
		result = prime * result + ((production == null) ? 0 : production.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Movie anotherMovie = (Movie) obj;
		if (allocated != anotherMovie.allocated)
			return false;
		if (production == null) {
			if (anotherMovie.production != null)
				return false;
		} else if (!production.equals(anotherMovie.production))
			return false;
		if (title == null) {
			if (anotherMovie.title != null)
				return false;
		} else if (!title.equals(anotherMovie.title))
			return false;
		return true;
	}

}
