package com.yash.mbs.exception;

public class EmptyValueException extends RuntimeException {
	public EmptyValueException(String errorMessage){
		super(errorMessage);
	}
}
