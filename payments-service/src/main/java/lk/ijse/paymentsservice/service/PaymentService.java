package lk.ijse.paymentsservice.service;

import lk.ijse.paymentsservice.dto.PaymentDTO;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ResponseEntity<?> savePayment(PaymentDTO paymentDTO);
}
