package com.yash.mbs.exception;

public class AlreadyExistException extends RuntimeException {

	public AlreadyExistException(String errorMessage) {
		super(errorMessage);
	}
}
