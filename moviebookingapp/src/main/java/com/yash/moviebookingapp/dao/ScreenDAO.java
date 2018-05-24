package com.yash.moviebookingapp.dao;

import java.util.Set;

import com.yash.moviebookingapp.model.Screen;

public interface ScreenDAO {

	int insertScreen(Screen screenToBeAdded);

	Set<Screen> getAllScreen();

}
