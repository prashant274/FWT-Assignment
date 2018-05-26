package com.yash.moviebookingapp.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.yash.moviebookingapp.exception.EmptyFileException;
import com.yash.moviebookingapp.exception.FileNotExistException;

public class FileUtilTest {

	private FileUtil fileUtil;
	
	@Before
	public void init() {
		fileUtil=new FileUtil();
	}
	
	@Test(expected=FileNotExistException.class)
	public void readJsonFile_NonExistFilePathGiven_FileNotExistException() throws FileNotExistException {
		String nonExistFilePath="./src/test/resources/com.yash.mbs.jsonfiles/nonExistingFile.json";
		fileUtil.readJsonFile(nonExistFilePath);
	}

	@Test(expected=EmptyFileException.class)
	public void readJsonFile_NonExistFilePathGiven_EmptyFileException() throws FileNotExistException {
		String emptyFilePath="./src/test/resources/jsonfiles/EmpyFile.json";
		fileUtil.readJsonFile(emptyFilePath);
	}

	@Test
	public void readJsonFile_ValidFilePresent_ShouldReturnJsonString() throws Exception {
		String validFilePath="./src/test/resources/jsonfiles/validJsonFile.json";
		String jsonString=fileUtil.readJsonFile(validFilePath);
		String fileContent="[{\"id\":101,\"screenName\":\"Audi1\"},{\"id\":102,\"screenName\":\"Audi2\"}]";
		assertEquals(fileContent, jsonString);
	}
	
	@Test
	public void writeJsonStringToFile_NonNullJsonStringGiven_ShouldReturnOne() throws Exception {
		String validFilePath="./src/test/resources/jsonfiles/validJsonFile.json";
		String jsonToStore="[{\"id\":101,\"screenName\":\"Audi1\"},{\"id\":102,\"screenName\":\"Audi2\"}]";
		fileUtil.writeJsonStringToFile(validFilePath, jsonToStore);		
	}
}
