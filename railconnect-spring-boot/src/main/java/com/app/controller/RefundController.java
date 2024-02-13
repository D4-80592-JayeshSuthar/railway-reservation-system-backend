package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.RefundResposeDTO;
import com.app.entities.RefundEntity;
import com.app.service.RefundService;

@RestController
@RequestMapping("/refund")
@CrossOrigin
@Validated
public class RefundController {
		 @Autowired 
		 private RefundService refundService;

	@PostMapping(value = "/{bookingId}/tickets/{ticketId}/cancel/{amount}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void cancelTicket(
        @PathVariable Long bookingId,
        @PathVariable Long ticketId,
        @PathVariable Double amount) {
	
        RefundResposeDTO refund = refundService.cancelTicket(bookingId, ticketId,amount);
       
	    }
}
