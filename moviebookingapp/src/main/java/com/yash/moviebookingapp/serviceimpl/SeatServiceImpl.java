package com.yash.moviebookingapp.serviceimpl;

import com.yash.moviebookingapp.dao.SeatDAO;
import com.yash.moviebookingapp.model.Seat;
import com.yash.moviebookingapp.service.SeatService;

public class SeatServiceImpl implements SeatService {

	private SeatDAO seatDAO;

	public SeatServiceImpl(SeatDAO seatDAO) {
		this.seatDAO = seatDAO;
	}

	public int addSeat(Seat seat) {
		// TODO Auto-generated method stub
		return 1;
	}

}