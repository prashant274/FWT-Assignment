package com.yash.moviebookingapp.serviceimpl;

import java.util.Set;

import org.apache.log4j.Logger;

import com.yash.moviebookingapp.dao.ScreenDAO;
import com.yash.moviebookingapp.exception.AlreadyExistException;
import com.yash.moviebookingapp.exception.EmptyFileException;
import com.yash.moviebookingapp.exception.FileNotExistException;
import com.yash.moviebookingapp.exception.NullValueException;
import com.yash.moviebookingapp.model.Screen;
import com.yash.moviebookingapp.service.ScreenService;

public class ScreenServiceImpl implements ScreenService {

	private ScreenDAO screenDAO;

	private static final Logger logger = Logger.getLogger(ScreenServiceImpl.class);

	public ScreenServiceImpl(ScreenDAO screenDao) {
		this.screenDAO = screenDao;
	}

	public int addScreen(Screen screen) throws FileNotExistException {
		checkForNullScreenObject(screen);
		Set<Screen> screenSet = this.getAllScreen();
		checkForNullScreenSet(screenSet);
		checkIfThreeScreenAlreadyPresent(screenSet);
		return addScreenIfNotAlreadyPresent(screen, screenSet);
	}

	public Set<Screen> getAllScreen() throws FileNotExistException {
		Set<Screen> screenSet = null;
		try {
			screenSet = screenDAO.getAllScreen();
		} catch (FileNotExistException fileNotExistException) {
			throw fileNotExistException;
		} catch (EmptyFileException emptyFileException) {
			throw emptyFileException;
		}
		return screenSet;
	}

	public int updateScreen(Screen updatedScreen) throws FileNotExistException, EmptyFileException {
		checkForNullScreenObject(updatedScreen);
		Set<Screen> screenSet = this.getAllScreen();
		checkForNullScreenSet(screenSet);
		updateScreenWithNewScreen(updatedScreen, screenSet);
		int rowsAffected = screenDAO.updateScreen(updatedScreen);
		return rowsAffected;
	}

	private void updateScreenWithNewScreen(Screen updatedScreen, Set<Screen> screenSet) {
		if (isScreenAlreadyPresent(updatedScreen, screenSet)) {
			screenSet.remove(updatedScreen);
			screenSet.add(updatedScreen);
		}
	}

	private void checkForNullScreenSet(Set<Screen> screenSet) {
		if (isObjectNull(screenSet)) {
			throw new NullValueException("ScrenSet is null");
		}
	}

	private void checkIfThreeScreenAlreadyPresent(Set<Screen> screenSet) {
		if (screenSet.size() >= 3) {
			throw new AlreadyExistException("Can't add more than 3 screen");
		}
	}

	private int addScreenIfNotAlreadyPresent(Screen screen, Set<Screen> screenSet) throws FileNotExistException {
		if (!isScreenAlreadyPresent(screen, screenSet)) {
			screenDAO.insertScreen(screen);
			int rowsAffected = 1;
			return rowsAffected;
		} else
			throw new AlreadyExistException("Screen already exists");
	}

	private boolean isScreenAlreadyPresent(Screen screen, Set<Screen> screenSet) {
		return screenSet.contains(screen);
	}

	private void checkForNullScreenObject(Screen screen) {
		if (isObjectNull(screen)) {
			throw new NullValueException("screen is null");
		}
	}

	private boolean isObjectNull(Object screen) {
		return screen == null;
	}

}
