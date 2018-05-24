package com.yash.moviebookingapp.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.yash.moviebookingapp.dao.SeatDAO;
import com.yash.moviebookingapp.model.Seat;
import com.yash.moviebookingapp.service.SeatService;

public class SeatServiceImplTest {

	@Test
	public void addSeat_seatObjectGiven_ShouldAddSeatAndReturnOne() {
		SeatDAO seatDAO=mock(SeatDAO.class);
		SeatService seatService=new SeatServiceImpl(seatDAO);
		Seat seat=new Seat("P1","Premium");
		when(seatDAO.insertSeat(seat)).thenReturn(1);
		int rowsAffected=seatService.addSeat(seat);
		assertEquals(1, rowsAffected);
	}
	
}
