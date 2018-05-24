package com.yash.moviebookingapp.serviceimpl;

import java.util.HashSet;
import java.util.Set;

import com.yash.mbs.exception.AlreadyExistException;
import com.yash.mbs.exception.NullValueException;
import com.yash.moviebookingapp.dao.ScreenDAO;
import com.yash.moviebookingapp.model.Screen;
import com.yash.moviebookingapp.service.ScreenService;

public class ScreenServiceImpl implements ScreenService {

	private ScreenDAO screenDAO;

	public ScreenServiceImpl(ScreenDAO screenDao) {
		this.screenDAO = screenDao;
	}

	public int addScreen(Screen screen) {
		checkForNullScreenObject(screen);
		Set<Screen> screenSet = screenDAO.getAllScreen();
		screenSet = checkForNullScreenSet(screenSet);
		checkIfThreeScreenAlreadyPresent(screenSet);
		return addScreenIfNotAlreadyPresent(screen, screenSet);
	}

	private Set<Screen> checkForNullScreenSet(Set<Screen> screenSet) {
		if (isObjectNull(screenSet)) {
			screenSet = new HashSet<Screen>();
		}
		return screenSet;
	}

	private void checkIfThreeScreenAlreadyPresent(Set<Screen> screenSet) {
		if (screenSet.size() >= 3) {
			throw new AlreadyExistException("Can't add more than 3 screen");
		}
	}

	private int addScreenIfNotAlreadyPresent(Screen screen, Set<Screen> screenSet) {
		if (!isScreenAlreadyPresent(screen, screenSet)) {
			screenDAO.insertScreen(screen);
			return 1;
		} else
			throw new AlreadyExistException("Screen already exists");
	}

	private boolean isScreenAlreadyPresent(Screen screen, Set<Screen> screenSet) {
		return screenSet.contains(screen);
	}

	private void checkForNullScreenObject(Screen screen) {
		if (isObjectNull(screen)) {
			throw new NullValueException("screen to be add is null");
		}
	}

	private boolean isObjectNull(Object screen) {
		return screen == null;
	}

}
