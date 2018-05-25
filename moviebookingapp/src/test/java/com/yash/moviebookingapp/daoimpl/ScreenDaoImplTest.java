package com.yash.moviebookingapp.daoimpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.yash.moviebookingapp.dao.ScreenDAO;
import com.yash.moviebookingapp.exception.EmptyFileException;
import com.yash.moviebookingapp.exception.FileNotExistException;
import com.yash.moviebookingapp.model.Screen;
import com.yash.moviebookingapp.util.FileUtil;

public class ScreenDaoImplTest {

	private ScreenDAO screenDAO;

	private FileUtil fileUtil;

	@Before
	public void init() {
		fileUtil = mock(FileUtil.class);
		screenDAO = new ScreenDaoImpl(fileUtil);
	}

	@Test(expected = FileNotExistException.class)
	public void getAllScreen_FileNotPresent_ThrowFileNotExistException() throws FileNotExistException {
		String nonExitingFilePath = anyString();
		when(fileUtil.readJsonFile(nonExitingFilePath)).thenThrow(FileNotExistException.class);
		screenDAO.getAllScreen();
	}

	@Test(expected = EmptyFileException.class)
	public void getAllScreen_EmptyFilePresent_ThrowEmptyFileException() throws FileNotExistException {
		String emptyFilePath = anyString();
		when(fileUtil.readJsonFile(emptyFilePath)).thenThrow(EmptyFileException.class);
		screenDAO.getAllScreen();
	}

	@Test
	public void getAllScreen_validScreenFile_shouldReturnScreenSet() throws FileNotExistException {
		String validJsonFile = anyString();
		String screenJson="[{\"id\":101,\"screenName\":\"Audi1\"},{\"id\":102,\"screenName\":\"Audi2\"}]";
		Set<Screen> screenSetInFile = new HashSet<Screen>();
		screenSetInFile.add(new Screen(101, "Audi1"));
		screenSetInFile.add(new Screen(102, "Audi2"));
		when(fileUtil.readJsonFile(validJsonFile)).thenReturn(screenJson);
		Set<Screen> screenSet = screenDAO.getAllScreen();
		assertEquals(screenSetInFile.size(), screenSet.size());
	}


	@Test(expected = FileNotExistException.class)
	public void insertScreen_FileNotPresent_ThrowFileNotExistException() throws FileNotExistException {
		String nonExitingFilePath = anyString();
		String jsonString = anyString();
		when(fileUtil.writeJsonStringToFile(nonExitingFilePath,jsonString)).thenThrow(FileNotExistException.class);
		Screen screenToBeAdded=new Screen(101, "Audi101");
		screenDAO.insertScreen(screenToBeAdded);
	}
	
	
}
