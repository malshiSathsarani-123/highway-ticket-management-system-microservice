package lk.ijse.user_service.service;

import lk.ijse.user_service.dto.SignIn;
import lk.ijse.user_service.dto.SignUp;

public interface UserService {
    String registerUser(SignUp signUp) throws Exception;

    void verifyUser(SignIn signIn) throws Exception;
}