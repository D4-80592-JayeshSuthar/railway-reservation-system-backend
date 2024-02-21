package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.RefundDTO;
import com.app.dto.RefundResposeDTO;
import com.app.service.RefundService;

@RestController
@RequestMapping("/refund")
@Validated
@CrossOrigin("*")
public class RefundController {
	@Autowired 
	private RefundService refundService;

	@PostMapping(value = "/{bookingId}/tickets/{ticketId}/cancel",produces = MediaType.APPLICATION_JSON_VALUE)
    public void cancelTicket(
        @PathVariable Long bookingId,
        @PathVariable Long ticketId) {
        RefundResposeDTO refund = refundService.cancelTicket(bookingId, ticketId);
        System.out.println("Refund Details : "+refund);
	}
	
	 @GetMapping("/getAllRefundList")
	    public List<RefundDTO> getAllRefunds() {
	        return refundService.getAllRefunds();
	}
	 
	 @PostMapping("/updateStatus")
	    public ResponseEntity<String> updateRefundStatus(@RequestBody List<Long> ticketIds) {
	        try {
	            refundService.updateRefundStatus(ticketIds);
	            return ResponseEntity.ok("Refund status updated successfully");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating refund status: " + e.getMessage());
	        }
	   }
}
