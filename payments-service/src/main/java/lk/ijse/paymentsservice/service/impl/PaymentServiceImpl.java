package lk.ijse.paymentsservice.service.impl;

import lk.ijse.paymentsservice.dto.PaymentDTO;
import lk.ijse.paymentsservice.entity.PaymentEntity;
import lk.ijse.paymentsservice.entity.TicketEntity;
import lk.ijse.paymentsservice.exception.NotFoundException;
import lk.ijse.paymentsservice.repo.PaymentRepo;
import lk.ijse.paymentsservice.repo.TicketRepo;
import lk.ijse.paymentsservice.service.PaymentService;
import lk.ijse.paymentsservice.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private final PaymentRepo paymentRepo;

    @Autowired
    private final TicketRepo ticketRepo;

    @Autowired
    private final Mapping mapper;

    @Override
    public ResponseEntity<?> purchasePayment(PaymentDTO paymentDTO) {
        try {
            paymentDTO.setId(nextPaymentId());
            PaymentEntity payment = mapper.toPayment(paymentDTO);
            Optional<TicketEntity> byId = ticketRepo.findById(paymentDTO.getTicketId());
            if (!byId.isPresent()) throw new NotFoundException("Ticket Not Exists");
            payment.setTicketEntity(new TicketEntity(byId.get().getId(),byId.get().getDiverName(),byId.get().getDirection(),byId.get().getAmount(),byId.get().getStatus()));
            paymentRepo.save(payment);
            return ResponseEntity.ok("Payment Successfully");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getPaymentById(String id) {
        try {
            Optional<PaymentEntity> byId = paymentRepo.findById(id);
            if (!byId.isPresent()) throw new NotFoundException("Payment Not Exists");
            PaymentDTO paymentDTO = new PaymentDTO(byId.get().getId(), byId.get().getType(), byId.get().getDate(), byId.get().getAmount(), byId.get().getStatus(), byId.get().getTicketEntity().getId());
            return ResponseEntity.ok(paymentDTO);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(exception.getMessage());
        }
    }

    public String nextPaymentId() {
        String maxId = paymentRepo.findMaxId();
        if (maxId != null){
            return generateNextPaymentId(maxId);
        }else {
            return "P-001";
        }
    }

    private static String generateNextPaymentId(String lastPaymentId) {
        String numericPart = lastPaymentId.substring(2);
        int nextNumericValue = Integer.parseInt(numericPart) + 1;
        String nextNumericPart = String.format("%03d", nextNumericValue);
        return "P-" + nextNumericPart;
    }
}
