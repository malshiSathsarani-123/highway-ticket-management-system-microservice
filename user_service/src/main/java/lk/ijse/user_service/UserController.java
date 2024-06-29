package lk.ijse.user_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @GetMapping("/health")
    public String message(){
        return "User Service works fine ";
    }

    @PostMapping("/signing")
    public String signing(){
        return "signing";
    }
}
