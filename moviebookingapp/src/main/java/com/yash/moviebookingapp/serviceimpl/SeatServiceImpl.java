package com.yash.moviebookingapp.serviceimpl;

import com.yash.moviebookingapp.dao.SeatDAO;
import com.yash.moviebookingapp.exception.NullValueException;
import com.yash.moviebookingapp.model.Seat;
import com.yash.moviebookingapp.service.SeatService;

public class SeatServiceImpl implements SeatService {

	private SeatDAO seatDAO;
	
	public SeatServiceImpl(SeatDAO seatDAO) {
		this.seatDAO=seatDAO;
	}

	public int addSeatsToScreen(Seat seat) {
		checkForNullScreen(seat);
		int rowsAffected=seatDAO.addSeatToScreen(seat);
		return rowsAffected;
	}

	private void checkForNullScreen(Seat seat) {
		if(isObjectNull(seat)){
			throw new NullValueException("Seat object is null");
		}
	}

	private boolean isObjectNull(Seat seat) {
		return seat==null;
	}

}
