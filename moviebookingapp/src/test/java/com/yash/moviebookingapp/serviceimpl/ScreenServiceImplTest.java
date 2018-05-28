package com.yash.moviebookingapp.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.yash.moviebookingapp.dao.ScreenDAO;
import com.yash.moviebookingapp.exception.AlreadyExistException;
import com.yash.moviebookingapp.exception.EmptyFileException;
import com.yash.moviebookingapp.exception.FileNotExistException;
import com.yash.moviebookingapp.exception.NullValueException;
import com.yash.moviebookingapp.model.Movie;
import com.yash.moviebookingapp.model.Screen;
import com.yash.moviebookingapp.service.ScreenService;

public class ScreenServiceImplTest {

	private ScreenService screenService;

	private ScreenDAO screenDao;

	private Screen screen;

	@Before
	public void init() {
		screenDao = mock(ScreenDAO.class);
		screenService = new ScreenServiceImpl(screenDao);
		screen = new Screen(101, "Audi1");
	}

	
	@Test(expected = NullValueException.class)
	public void addScreen_NullScreenObjectGiven_ThrowNullValueException() throws FileNotExistException {
		Screen nullScreenObject = null;
		screenService.addScreen(nullScreenObject);
	}

	@Test(expected = NullValueException.class)
	public void addScreen_screenSetIsNull_ThrowNullValueException() throws FileNotExistException {
		when(screenDao.getAllScreen()).thenReturn(null);
		screenService.addScreen(screen);
	}

	@Test(expected = AlreadyExistException.class)
	public void addScreen_ThreeScreenAlreadyPresent_ThrowAlreadyExistException() throws FileNotExistException {
		List<Screen> alreadyExistScreens = Arrays.asList(new Screen(101, "Audi1"), new Screen(102, "Audi2"),
				new Screen(103, "Audi3"));
		when(screenDao.getAllScreen()).thenReturn(new HashSet<Screen>(alreadyExistScreens));
		screenService.addScreen(screen);
	}

	@Test(expected = AlreadyExistException.class)
	public void addScreen_AlreadyExistScreenGiven_ThrowAlreadyExistsException() throws FileNotExistException {
		List<Screen> alreadyExistScreens = Arrays.asList(new Screen(101, "Audi1"), new Screen(102, "Audi2"));
		when(screenDao.getAllScreen()).thenReturn(new HashSet<Screen>(alreadyExistScreens));
		screenService.addScreen(screen);
	}

	
	@Test
	public void addScreen_ScreenObjectGiven_ShouldAddScreenAndReturnOne() throws FileNotExistException {
		when(screenDao.insertScreen(screen)).thenReturn(1);
		int rowsAffected = screenService.addScreen(screen);
		assertEquals(1, rowsAffected);
	}
	
	@Test(expected = FileNotExistException.class)
	public void getAllScreen_ScreenJsonFileNotExist_ThrowFileNotExistsException() throws FileNotExistException {
		when(screenDao.getAllScreen()).thenThrow(FileNotExistException.class);
		Set<Screen> screens = screenService.getAllScreen();
	}

	@Test(expected = EmptyFileException.class)
	public void getAllScreen_ScreenJsonFileEmpty_ThrowEmptyFileException() throws FileNotExistException {
		when(screenDao.getAllScreen()).thenThrow(EmptyFileException.class);
		Set<Screen> screens = screenService.getAllScreen();
	}

	@Test(expected = NullValueException.class)
	public void updateScreen_NullScreenObjectPresent_ThrowNullValueException() throws FileNotExistException {
		Screen nullScreenObjec = null;
		screenService.updateScreen(nullScreenObjec);
	}

	@Test(expected = NullValueException.class)
	public void updateScreen_NullScreenSetPresent_ThrowNullValueException() throws FileNotExistException {
		when(screenDao.getAllScreen()).thenThrow(NullValueException.class);
		screenService.updateScreen(screen);
	}

	@Test
	public void updateScreen_UpdatedScreenPresent_ShouldNotShouldNotAddNewScreen() throws FileNotExistException {
		Set<Screen> alreadyExistScreenSet = new HashSet(Arrays.asList(new Screen(101, "Audi1"), new Screen(102, "Audi2")));
		Movie movieToAddScreen = new Movie("Title1", "Production1", Arrays.asList("Actor1", "Actor2"),
				Time.valueOf("03:00:00"));
		int expectedScreenSetSize = 2;
		screen.getAllottedMovies().add(movieToAddScreen);
		when(screenDao.getAllScreen()).thenReturn(alreadyExistScreenSet);
		screenService.updateScreen(screen);
		assertEquals(expectedScreenSetSize, alreadyExistScreenSet.size());
	}

	
	@Test(expected = FileNotExistException.class)
	public void updateScreen_ScreenJsonFileNotExist_ThrowFileNotExistsException() throws FileNotExistException {
		Set<Screen> alreadyExistScreenSet = anySet();
		when(screenDao.updateScreenSet(alreadyExistScreenSet)).thenThrow(FileNotExistException.class);
		screenService.updateScreen(screen);
	}

	@Test(expected = EmptyFileException.class)
	public void updateScreen_ScreenJsonFileEmpty_ThrowEmptyFileException() throws FileNotExistException {
		Set<Screen> alreadyExistScreenSet =anySet();
		when(screenDao.updateScreenSet(alreadyExistScreenSet)).thenThrow(EmptyFileException.class);
		screenService.updateScreen(screen);
	}

	@Test
	public void updateScreen_UpdatedScreenPresent_ShuoulUpdateScreenSetAndRetunOne() throws FileNotExistException {
		Set<Screen> alreadyExistScreenSet = anySet();
		Movie movieToAddScreen = new Movie("Title1", "Production1", Arrays.asList("Actor1", "Actor2"),
				Time.valueOf("03:00:00"));
		screen.getAllottedMovies().add(movieToAddScreen);
		when(screenDao.updateScreenSet(alreadyExistScreenSet)).thenReturn(1);
		int rowsAffected=screenService.updateScreen(screen);
		assertEquals(1, rowsAffected);
	}
	
}

