package com.yash.moviebookingapp.serviceimpl;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.yash.moviebookingapp.dao.SeatDAO;
import com.yash.moviebookingapp.exception.NullValueException;
import com.yash.moviebookingapp.model.Seat;
import com.yash.moviebookingapp.service.SeatService;

public class SeatServiceImplTest {

	private SeatDAO seatDAO;
	
	private SeatService seatService;
	
	@Before
	public void init() {
		seatDAO=mock(SeatDAO.class);
		seatService=new SeatServiceImpl(seatDAO);
	}
	
	@Test(expected=NullValueException.class)
	public void addSeatsToScreen_NullSeatObjectGiven_ThrowNullValueException() {
		Seat nullScreen=null;
		seatService.addSeatsToScreen(nullScreen);
	}

}
