package com.yash.moviebookingapp.model;

public class Seat {

	private String seatNo;
	private boolean allocated;
	
	public Seat(String seatNo, boolean allocated) {
		super();
		this.seatNo = seatNo;
		this.allocated = allocated;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public boolean isAllocated() {
		return allocated;
	}

	
	
}
