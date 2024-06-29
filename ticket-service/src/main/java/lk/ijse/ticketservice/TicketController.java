package lk.ijse.ticketservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ticket")
public class TicketController {

    @GetMapping("/health")
    public String message(){
        return "User Service works fine ";
    }

    @PostMapping("/signing")
    public String signing(){
        return "signing";
    }
}
