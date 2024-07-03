package lk.ijse.paymentsservice.service.impl;

import lk.ijse.paymentsservice.dto.PaymentDTO;
import lk.ijse.paymentsservice.repo.PaymentRepository;
import lk.ijse.paymentsservice.service.PaymentService;
import lk.ijse.paymentsservice.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private final PaymentRepository paymentRepository;

    @Autowired
    private final Mapping mapper;
    @Override
    public ResponseEntity<?> savePayment(PaymentDTO paymentDTO) {
        try {
            paymentRepository.save(mapper.toPayment(paymentDTO));
            return ResponseEntity.ok("Payment saved successfully");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(exception.getMessage());
        }
    }
}
