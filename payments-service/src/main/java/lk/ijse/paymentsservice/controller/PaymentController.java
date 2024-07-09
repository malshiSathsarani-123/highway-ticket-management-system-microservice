package lk.ijse.paymentsservice.controller;

import lk.ijse.paymentsservice.dto.PaymentDTO;
import lk.ijse.paymentsservice.exception.NotFoundException;
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
    public ResponseEntity<?> purchasePayment(@RequestBody PaymentDTO paymentDTO) {
        try {
            return ResponseEntity.ok(paymentService.purchasePayment(paymentDTO));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error | Payment failed.");
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(paymentService.getPaymentById(id));
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error | Failed to fetch Payment Details.");
        }
    }
}
