package com.yash.moviebookingapp.service;

import java.util.Set;

import com.yash.moviebookingapp.exception.EmptyFileException;
import com.yash.moviebookingapp.exception.FileNotExistException;
import com.yash.moviebookingapp.model.Screen;

public interface ScreenService {

	int addScreen(Screen screen) throws FileNotExistException;

	Set<Screen> getAllScreen() throws FileNotExistException;

	int updateScreen(Screen updatedScreen) throws FileNotExistException, EmptyFileException;

}
