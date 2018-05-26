package com.yash.moviebookingapp.serviceimpl;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.yash.moviebookingapp.dao.ScreenDAO;
import com.yash.moviebookingapp.daoimpl.MovieServiceImpl;
import com.yash.moviebookingapp.daoimpl.ScreenDaoImpl;
import com.yash.moviebookingapp.exception.NullValueException;
import com.yash.moviebookingapp.exception.TimeSloteException;
import com.yash.moviebookingapp.model.Movie;
import com.yash.moviebookingapp.model.Screen;
import com.yash.moviebookingapp.service.MovieService;
import com.yash.moviebookingapp.util.FileUtil;

public class MovieServiceImplTest {

	private MovieService movieService;
	private FileUtil fileUtil=new FileUtil();
	private ScreenDAO screenDAO=new ScreenDaoImpl(fileUtil);
	private ScreenServiceImpl screenService=new ScreenServiceImpl(screenDAO);
	private Screen screen;
	private Movie movie;
	
	@Before
	public void init() {
		movieService = new MovieServiceImpl(screenService);
		screen = new Screen(101, "Audi2");
		movie = new Movie("Title1", "Production1", Arrays.asList("Actor1", "Actor2", "Actor3"), Time.valueOf("03:15:00"));
	}

	@Test(expected = NullValueException.class)
	public void addMovieToScreen_NullMovieObjectGiven_ThrowNullValueException() throws Exception {
		Movie nullMovieObject = null;
		int rowsAffected = movieService.addMovieToScreen(nullMovieObject, screen);
	}

	@Test(expected = NullValueException.class)
	public void addMovieToScreen_NullScrenObjectGiven_ThrowNullValueException() throws Exception {
		Screen nullScreen = null;
		int rowsAffected = movieService.addMovieToScreen(movie, nullScreen);
	}

	@Test(expected = TimeSloteException.class)
	public void addMovieToScreen_ScreenAvailableTimeSlotLessThanMovieDuration_ThrowTimeSloteException()
			throws Exception {
		Screen screen = new Screen(101, "Audi1");
		String movieDuration = "03:15:00";
		Movie movieTobeAdded = new Movie("T1", "P1", Arrays.asList("A1", "A2", "A3"), Time.valueOf(movieDuration));
		String availableTimeInHours = "03:00:00";
		screen.setAvailableTimeSlot(Time.valueOf(availableTimeInHours));
		movieService.addMovieToScreen(movieTobeAdded, screen);
	}
	
	@Test
	public void addMovieToScreen_ShouldReduceScreenTimeSlotByHouseKeepingDurationBeforeAddMovie()
			throws Exception {
		String initialScreenTime = "04:15:00";
		String movieDuration = "03:00:00";
		String screenRemainingTimeAfterReduction="03:15:00";
		Movie movieTobeAdded = new Movie("T1", "P1", Arrays.asList("A1", "A2", "A3"), Time.valueOf(movieDuration));
		screen.setAvailableTimeSlot(Time.valueOf(initialScreenTime));
		movieService.addMovieToScreen(movieTobeAdded, screen);
		assertEquals(screenRemainingTimeAfterReduction, screen.getAvailableTimeSlot().toString());
	}
	
	@Test
	public void addMovieToScreen_ShouldReduceScreenTimeSlotByMovieDurationBeforeAdd()
			throws Exception {
		String initialScreenTime = "04:15:00";
		String movieDuration = "03:00:00";
		String screenRemainingTimeAfterReduction="00:15:00";
		Movie movieTobeAdded = new Movie("T1", "P1", Arrays.asList("A1", "A2", "A3"), Time.valueOf(movieDuration));
		screen.setAvailableTimeSlot(Time.valueOf(initialScreenTime));
		movieService.addMovieToScreen(movieTobeAdded, screen);
		assertEquals(screenRemainingTimeAfterReduction, screen.getAvailableTimeSlot().toString());
	}
	
	@Test
	public void addMovieToScreen_ScreenAvailableTimeSlotMoreThanMovieDuration_ShouldAddMovieToScreen()
			throws Exception {
		Screen screen = new Screen(101, "Audi1");
		String availableTimeInHours = "03:15:00";
		String movieDuration = "03:00:00";
		Movie movieTobeAdded = new Movie("T1", "P1", Arrays.asList("A1", "A2", "A3"), Time.valueOf(movieDuration));
		screen.setAvailableTimeSlot(Time.valueOf(availableTimeInHours));
		movieService.addMovieToScreen(movieTobeAdded, screen);
	}

}
