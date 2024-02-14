package com.app.service;

import java.util.List;

import com.app.dto.RefundDTO;
import com.app.dto.RefundResposeDTO;


public interface RefundService {
	RefundResposeDTO cancelTicket(Long bookingId, Long ticketId,Double amount);

	List<RefundDTO> getAllRefunds();


	void updateRefundStatus(List<Long> ticketIds);

	
	
}
