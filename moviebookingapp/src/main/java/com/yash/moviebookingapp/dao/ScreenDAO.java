package com.yash.moviebookingapp.dao;

import java.util.Set;

import com.yash.moviebookingapp.exception.EmptyFileException;
import com.yash.moviebookingapp.exception.FileNotExistException;
import com.yash.moviebookingapp.model.Screen;

public interface ScreenDAO {

	int insertScreen(Screen screenToBeAdded)  throws FileNotExistException;

	Set<Screen> getAllScreen() throws FileNotExistException,EmptyFileException;

}
