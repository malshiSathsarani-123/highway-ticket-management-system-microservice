package lk.ijse.user_service.util;

import lk.ijse.user_service.dto.SignUp;
import lk.ijse.user_service.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Mapping {

    final private ModelMapper modelMapper;

    public UserEntity convertToUserEntity(SignUp signUp){
        return modelMapper.map(signUp, UserEntity.class);
    }
}
