package lk.ijse.user_service.controller;
import lk.ijse.user_service.dto.SignIn;
import lk.ijse.user_service.dto.SignUp;
import lk.ijse.user_service.exception.InvalidCredentialException;
import lk.ijse.user_service.exception.NotFoundException;
import lk.ijse.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public String message(){
        return "User Service works fine ";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUp signUp) {
        try {
            return ResponseEntity.ok(userService.registerUser(signUp));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(exception.getMessage());
        }
    }


    @PostMapping("/signIn")
    public ResponseEntity<?> verifyUser(@RequestBody SignIn signIn){
        try {
            userService.verifyUser(signIn);
            return ResponseEntity.status(HttpStatus.CREATED).body("User Details Verify Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(exception.getMessage());
        }
    }
}
