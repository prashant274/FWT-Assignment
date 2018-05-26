package com.yash.moviebookingapp.service;

import com.yash.moviebookingapp.exception.TimeSloteException;
import com.yash.moviebookingapp.model.Movie;
import com.yash.moviebookingapp.model.Screen;

public interface MovieService {

	int addMovieToScreen(Movie movie,Screen screen) throws TimeSloteException;

}
