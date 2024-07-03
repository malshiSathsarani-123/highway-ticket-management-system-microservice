package lk.ijse.paymentsservice.controller;

import lk.ijse.paymentsservice.dto.PaymentDTO;
import lk.ijse.paymentsservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/health")
    public String message(){
        return "Payment Service works fine ";
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody PaymentDTO paymentDTO) {
        try {
            return ResponseEntity.ok(paymentService.savePayment(paymentDTO));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(exception.getMessage());
        }
    }

}
