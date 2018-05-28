package com.yash.moviebookingapp.util;

import com.yash.moviebookingapp.exception.FileNotExistException;


public class MenuUtil {


	private final static String operatorMenuFilePath=""; 
	private FileUtil fileUtil;
	
	public void showMainMenu() {
		fileUtil=new FileUtil();
		try {
		String OperatorMenus=fileUtil.readFile("./src/main/resources/menuFile/OperatorMenu.txt");
			System.out.println(OperatorMenus);
		} catch (FileNotExistException fileException) {
			System.out.println(fileException.getMessage());
		}
	}

	
	
}
