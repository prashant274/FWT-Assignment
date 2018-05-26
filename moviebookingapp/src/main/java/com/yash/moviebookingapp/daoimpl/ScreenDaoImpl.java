package com.yash.moviebookingapp.daoimpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.yash.moviebookingapp.dao.ScreenDAO;
import com.yash.moviebookingapp.exception.EmptyFileException;
import com.yash.moviebookingapp.exception.FileNotExistException;
import com.yash.moviebookingapp.model.Screen;
import com.yash.moviebookingapp.util.FileUtil;


public class ScreenDaoImpl implements ScreenDAO {

	private FileUtil fileUtil;
	private static final String filePath="//src//main//resources//screenJson.json";
	
	
	public ScreenDaoImpl(FileUtil fileUtil) {
		this.fileUtil=fileUtil;
	}

	public int insertScreen(Screen screenToBeAdded) throws FileNotExistException{
		Gson gson=new Gson();
		int rowsAffected=0;
		Set<Screen> screenSet=getAllScreen();
		screenSet.add(screenToBeAdded);
		String screenSetJson=gson.toJson(screenSet);
		rowsAffected=fileUtil.writeJsonStringToFile(filePath, screenSetJson);
		return rowsAffected;
	}

	public Set<Screen> getAllScreen() throws FileNotExistException,EmptyFileException {
		String screenJsonString=fileUtil.readJsonFile(filePath);
		Gson gson=new Gson();
		Screen[] screensFromJsonFile=gson.fromJson(screenJsonString, Screen[].class);
		Set<Screen> screenSet=new HashSet<Screen>(Arrays.asList(screensFromJsonFile));
		return screenSet;
	}

	public int updateScreen(Screen updatedScreen) throws FileNotExistException, EmptyFileException {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
