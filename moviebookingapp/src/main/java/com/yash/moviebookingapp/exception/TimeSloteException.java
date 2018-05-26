package com.yash.moviebookingapp.exception;

import javax.management.InvalidAttributeValueException;

public class TimeSloteException extends InvalidAttributeValueException {
	public TimeSloteException(String errorMessage) {
		super(errorMessage);
	}
}
