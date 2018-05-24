package com.yash.moviebookingapp.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yash.mbs.exception.AlreadyExistException;
import com.yash.mbs.exception.NullValueException;
import com.yash.moviebookingapp.dao.ScreenDAO;
import com.yash.moviebookingapp.model.Screen;
import com.yash.moviebookingapp.service.ScreenService;

public class ScreenServiceImplTest {

	private ScreenService screenService;
	
	private ScreenDAO screenDao;
	
	@Before
	public void init() {
		screenDao=mock(ScreenDAO.class);
		screenService=new ScreenServiceImpl(screenDao);
	}
	
	@Test
	public void addScreen_ScreenObjectGiven_ShouldAddScreenAndReturnOne() {
		Screen screen=new Screen(101,"Audi-1");
		when(screenDao.insertScreen(screen)).thenReturn(1);
		int rowsAffected=screenService.addScreen(screen);
		assertEquals(1, rowsAffected);
	}
	
	
	@Test(expected=NullValueException.class)
	public void addScreen_NullScreenObjectGiven_ThrowNullValueException(){
		Screen nullScreenObject=null;
	    screenService.addScreen(nullScreenObject);
	}

	@Test(expected=NullValueException.class)
	public void addScreen_screenSetIsNull_ThrowNullValueException(){
		Screen screen=new Screen(104, "Audi4");
	    screenService.addScreen(screen);
	}
	
	@Test(expected=AlreadyExistException.class)
	public void addScreen_ThreeScreenAlreadyPresent_ThrowAlreadyExistException() {
		Screen screen=new Screen(104, "Audi4");
		List<Screen> alreadyExistScreens=Arrays.asList(new Screen(101, "Audi1"),new Screen(102, "Audi2"),new Screen(103, "Audi3"));
		when(screenDao.getAllScreen()).thenReturn(new HashSet<Screen>(alreadyExistScreens));
		screenService.addScreen(screen);
	}
	
	@Test(expected=AlreadyExistException.class)
	public void addScreen_AlreadyExistScreenGiven_ThrowAlreadyExistsException(){
		Screen screen=new Screen(101, "Audi1");
		List<Screen> alreadyExistScreens=Arrays.asList(new Screen(101, "Audi1"),new Screen(102, "Audi2"));
		when(screenDao.getAllScreen()).thenReturn(new HashSet<Screen>(alreadyExistScreens));
		screenService.addScreen(screen);
	}
	
}
