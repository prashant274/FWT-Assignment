package com.yash.moviebookingapp.exception;

import java.io.FileNotFoundException;

public class FileNotExistException extends FileNotFoundException {

	public FileNotExistException(String errorMessage) {
		super(errorMessage);
	}
}
