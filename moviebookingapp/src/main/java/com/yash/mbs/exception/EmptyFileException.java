package com.yash.mbs.exception;

public class EmptyFileException extends RuntimeException {

	public EmptyFileException(String errorMessage) {
		super(errorMessage);
	}
}
