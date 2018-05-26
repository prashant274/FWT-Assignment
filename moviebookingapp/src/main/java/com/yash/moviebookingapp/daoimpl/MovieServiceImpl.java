package com.yash.moviebookingapp.daoimpl;

import java.sql.Time;

import com.yash.moviebookingapp.enums.TimeSlot;
import com.yash.moviebookingapp.exception.NullValueException;
import com.yash.moviebookingapp.exception.TimeSloteException;
import com.yash.moviebookingapp.model.Movie;
import com.yash.moviebookingapp.model.Screen;
import com.yash.moviebookingapp.service.MovieService;
import com.yash.moviebookingapp.service.ScreenService;

public class MovieServiceImpl implements MovieService {

	private ScreenService screenService;

	public MovieServiceImpl(ScreenService screenService) {
		this.screenService = screenService;
	}

	public int addMovieToScreen(Movie movie, Screen screen) throws TimeSloteException {
		checkForNullMovieObject(movie);
		checkForNullScreenObject(screen);
		checkIfMovieDurationMoreThanAvailableScreenTime(movie, screen);
		reduceScreenTimeByHousKeepingDuration(screen);
		reduceScreenTimeByMovieDuration(movie, screen);
		screen.getAllottedMovies().add(movie);
		// screenService.addScreen(screen)
		return 0;
	}

	private void reduceScreenTimeByMovieDuration(Movie movie, Screen screen) {
		Time screenTimeSlot = screen.getAvailableTimeSlot();
		Time movieDuration = movie.getDuration();
		String remainingScrenTimeSlot = calculateTimeDifference(screenTimeSlot, movieDuration);
		setScreenRemainingTimeSlot(screen, remainingScrenTimeSlot);
	}

	private void setScreenRemainingTimeSlot(Screen screen, String remainingScrenTimeSlot) {
		Time remainingTimeslot = Time.valueOf(remainingScrenTimeSlot);
		screen.setAvailableTimeSlot(remainingTimeslot);
	}

	private void reduceScreenTimeByHousKeepingDuration(Screen screen) {
		Time screenTimeSlot = screen.getAvailableTimeSlot();
		Time houseKeepingTime = Time.valueOf(TimeSlot.HOUSEKEEPING.getRequiredTime());
		String remainingScrenTimeSlot = calculateTimeDifference(screenTimeSlot, houseKeepingTime);
		setScreenRemainingTimeSlot(screen, remainingScrenTimeSlot);
	}

	private String calculateTimeDifference(Time minuend, Time substrahend) {
		long remainingTimeInSeconds = minuend.getTime() - substrahend.getTime();
		long totalRemainingMinuts = remainingTimeInSeconds / 60000;
		int remainingHours = (int) totalRemainingMinuts / 60;
		int remainingMinutes = (int) totalRemainingMinuts % 60;
		String remainingScrenTimeSlot = "" + remainingHours + ":" + remainingMinutes + ":" + "00";
		return remainingScrenTimeSlot;
	}

	private void checkIfMovieDurationMoreThanAvailableScreenTime(Movie movie, Screen screen) throws TimeSloteException {
		if (isMovieDurationMoreThanAvailableScreenTime(movie, screen)) {
			throw new TimeSloteException("movie duration is greater than availble screen time slot");
		}
	}

	private boolean isMovieDurationMoreThanAvailableScreenTime(Movie movie, Screen screen) {
		return movie.getDuration().getTime() >= screen.getAvailableTimeSlot().getTime();
	}

	private void checkForNullScreenObject(Screen screen) {
		if (isObjectNull(screen)) {
			throw new NullValueException("screen object is null");
		}
	}

	private void checkForNullMovieObject(Movie movie) {
		if (isObjectNull(movie)) {
			throw new NullValueException("movie object is null");
		}
	}

	private boolean isObjectNull(Object nullableObject) {
		return nullableObject == null;
	}

}
