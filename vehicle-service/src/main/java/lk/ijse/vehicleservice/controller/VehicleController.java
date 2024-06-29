package lk.ijse.vehicleservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {

//    @Autowired
//    private final TicketService ticketService;

    @GetMapping("/health")
    public String message(){
        return "User Service works fine ";
    }

//    @PostMapping("/createTicket")
//    public ResponseEntity<?> createTicket(@RequestBody TicketDTO ticketDTO){
//        try {
//            return ResponseEntity.ok(ticketService.crateTicket(ticketDTO));
//        } catch (Exception exception) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
//                    body(exception.getMessage());
//        }
//    }
//    @PutMapping("/updateStatus")
//    public ResponseEntity<?> updateStatus(@RequestBody TicketDTO ticketDTO){
//        try {
//            return ResponseEntity.ok(ticketService.updateStatus(ticketDTO));
//        } catch (Exception exception) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
//                    body(exception.getMessage());
//        }
//    }
//    @GetMapping("/retrieval")
//    public ResponseEntity<?> retrieval(){
//        try {
//            return ResponseEntity.ok(ticketService.retrieval());
//        } catch (Exception exception) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
//                    body(exception.getMessage());
//        }
//    }
}
