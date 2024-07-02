package lk.ijse.vehicleservice.controller;


import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    @Autowired
    private final VehicleService vehicleService;

    @GetMapping("/health")
    public String message(){
        return "User Service works fine ";
    }

    @PostMapping("/registerVehicle")
    public ResponseEntity<?> createTicket(@RequestBody VehicleDTO vehicleDTO){
        try {
            return ResponseEntity.ok(vehicleService.crateTicket(vehicleDTO));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(exception.getMessage());
        }
    }
    @PutMapping("/updateVehicle")
    public ResponseEntity<?> updateVehicle(@RequestBody VehicleDTO vehicleDTO){
        try {
            return ResponseEntity.ok(vehicleService.updateVehicle(vehicleDTO));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(exception.getMessage());
        }
    }
    @GetMapping("/retrieval")
    public ResponseEntity<?> retrieval(){
        try {
            return ResponseEntity.ok(vehicleService.retrieval());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(exception.getMessage());
        }
    }
}
