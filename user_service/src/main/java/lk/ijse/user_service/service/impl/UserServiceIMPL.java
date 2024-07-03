package lk.ijse.user_service.service.impl;

import lk.ijse.user_service.dto.SignIn;
import lk.ijse.user_service.dto.SignUp;
import lk.ijse.user_service.entity.UserEntity;
import lk.ijse.user_service.exception.InvalidCredentialException;
import lk.ijse.user_service.exception.NotFoundException;
import lk.ijse.user_service.repo.UserRepository;
import lk.ijse.user_service.service.UserService;
import lk.ijse.user_service.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final Mapping mapping;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String registerUser(SignUp signUp) throws Exception {
        try {
            signUp.setId(nextUserId());
            UserEntity user = mapping.convertToUserEntity(signUp);
            user.setPassword(bCryptPasswordEncoder.encode(signUp.getPassword()));
            UserEntity savedUser = userRepository.save(user);
            return "Saved User From UserId : " + savedUser.getId();
        } catch (DataIntegrityViolationException exception) {
            throw new Exception("User Already Exists");
        }
    }

    @Override
    public void updateUser(SignUp signUp, String id) throws Exception {
        try {
            Optional<UserEntity> userOptional = userRepository.findById(id);
            if (!userOptional.isPresent()) throw new NotFoundException("User Not Found");

            UserEntity user = userOptional.get();

            if (!user.getEmail().equals(signUp.getEmail())) {
                if (userRepository.existsByEmail(signUp.getEmail())) {
                    throw new Exception("Email is already in use by another user");
                }
            }
            user.setName(signUp.getName());
            user.setEmail(signUp.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(signUp.getPassword()));
            user.setRole(signUp.getRole());
        } catch (DataIntegrityViolationException exception) {
            throw new Exception("User Already Exists with this email");
        }
    }

    @Override
    public void verifyUser(SignIn signIn) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(signIn.getEmail());
        if (userOptional.isEmpty()) throw new NotFoundException("User Not Found");
        UserEntity userEntity = userOptional.get();

        if (!bCryptPasswordEncoder.matches(signIn.getPassword(), userEntity.getPassword())) {
            throw new InvalidCredentialException("User Credential Not Valid");
        }
    }
    public String nextUserId() {
        String maxId = userRepository.findMaxId();
        if (maxId != null){
            return generateNextUserId(maxId);
        }else {
            return "U-001";
        }
    }

    private static String generateNextUserId(String lastUserId) {
        String numericPart = lastUserId.substring(2);
        int nextNumericValue = Integer.parseInt(numericPart) + 1;
        String nextNumericPart = String.format("%03d", nextNumericValue);
        return "U-" + nextNumericPart;
    }
}