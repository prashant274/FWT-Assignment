package com.yash.moviebookingapp.exception;

public class EmptyFileException extends RuntimeException {

	public EmptyFileException(String errorMessage) {
		super(errorMessage);
	}
}
