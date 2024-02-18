package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.CancellationNotAllowedException;
import com.app.custom_exceptions.InvalidBookingException;
import com.app.custom_exceptions.TicketNotFoundException;
import com.app.dao.BookingDAO;
import com.app.dao.RefundDAO;
import com.app.dao.TicketDAO;
import com.app.dto.RefundDTO;
import com.app.dto.RefundResposeDTO;
import com.app.entities.BookingEntity;
import com.app.entities.RefundEntity;
import com.app.entities.RefundReasons;
import com.app.entities.TicketEntity;
import com.app.entities.TicketStatus;

@Service
@Transactional
public class RefundServiceImpl implements RefundService {
	
	@Autowired
	private BookingDAO bookingDao;

	@Autowired
	private TicketDAO ticketDao;

	@Autowired
	private RefundDAO refundDao;
	
	@Override
    public RefundResposeDTO cancelTicket(Long bookingId, Long ticketId) 
    		throws InvalidBookingException, TicketNotFoundException, CancellationNotAllowedException {
        BookingEntity booking = bookingDao.findById(bookingId)
                .orElseThrow(() -> new InvalidBookingException("Booking not found with ID: " + bookingId));
        TicketEntity ticket = ticketDao.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + ticketId));

        // 3. Ensure ticket belongs to the specified booking
        if (!ticket.getBooking().getPnrNumber().equals(bookingId)) {
            throw new InvalidBookingException("Ticket " + ticketId + " does not belong to booking " + bookingId);
        }

        // 4. Check if ticket cancellation is eligible based on your rules (cancellation window, etc.)
        // BOOKING Related rules CAN BE MENTIONED IN FAQs or somewhere else in UI (low priority)
        if (!canCancelTicket(ticket,bookingId)) {
            throw new CancellationNotAllowedException("Ticket cancellation not allowed due to booking rules");
        }
        
     
        // Here we have to implement the logic of amount which will come from frontend
        
        int numberOfTickets = booking.getTickets().size();
        Double amountPerTicket = booking.getTotalAmount() / numberOfTickets;
        
        // 5. Calculate refund amount (if applicable)
        Double refundAmount = calculateRefundAmount(amountPerTicket); // Implement your refund calculation logic
        

        // 7. Update ticket status to "CANCELLED"
        ticket.setStatus(TicketStatus.CANCEL);
        ticketDao.save(ticket);
        
        

        // 8. Create and save a refund entity (if applicable)
        RefundResposeDTO rrdto = createRefund(ticket, refundAmount);
        return rrdto; // Return the refund entity
   
    }

    private boolean canCancelTicket(TicketEntity ticket,Long bookingId) {
    	BookingEntity booking = bookingDao.findById(bookingId)
                .orElseThrow(() -> new InvalidBookingException("Booking not found with ID: " + bookingId));
         LocalDate bookingDate = booking.getDateOfJourney();
         LocalDate currentDate = LocalDate.now();
         int cancellationWindow = 1; // Replace with your cancellation window rule
         return currentDate.isBefore(bookingDate.plusDays(cancellationWindow));
    }

    private Double calculateRefundAmount(Double amount) {
        return amount * 0.75; 
    }

    private RefundResposeDTO createRefund(TicketEntity ticket, Double refundAmount) {
        // Create and populate a RefundEntity with relevant details
        RefundEntity refund = new RefundEntity();
         refund.setAmount(refundAmount);
         refund.setTicket(ticket);
         refund.setReason(RefundReasons.TICKET_CANCEL); 
         refund.setRefundStatus(false);
         refundDao.save(refund);
         RefundResposeDTO rrDTO = new RefundResposeDTO(HttpStatus.OK, "Ticket canceled successfully");
        return rrDTO;
    }
    
    public List<RefundDTO> getAllRefunds() {
        List<RefundEntity> refunds = refundDao.findAll();
        return refunds.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private RefundDTO mapToDTO(RefundEntity refundEntity) {
        return new RefundDTO(
                refundEntity.getTicket().getBooking().getTrain().getTrainName(),
                refundEntity.getTicket().getBooking().getTrain().getTrainNumber(),
                refundEntity.getTicket().getId(),
                refundEntity.getReason(),
                refundEntity.getAmount(),
                refundEntity.getRefundStatus()
        );
    }

    public void updateRefundStatus(List<Long> ticketIds) {
        List<RefundEntity> refundEntities = refundDao.findByTicketIdIn(ticketIds);
        for (RefundEntity refundEntity : refundEntities) {
            refundEntity.setRefundStatus(true);
        }
        refundDao.saveAll(refundEntities);
    }

}
