package com.yash.moviebookingapp.exception;

public class AlreadyExistException extends RuntimeException {

	public AlreadyExistException(String errorMessage) {
		super(errorMessage);
	}
}
