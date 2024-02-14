package com.app.service;

import com.app.dto.RefundResposeDTO;
import com.app.entities.RefundEntity;

public interface RefundService {
	RefundResposeDTO cancelTicket(Long bookingId, Long ticketId,Double amount);
	
}
