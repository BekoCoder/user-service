package uz.pdp.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.userservice.dto.AuthenticationRequest;
import uz.pdp.userservice.dto.AuthenticationResponce;
import uz.pdp.userservice.dto.UserDto;
import uz.pdp.userservice.entity.UserEntity;
import uz.pdp.userservice.exception.CustomException;
import uz.pdp.userservice.exception.UserNotFoundException;
import uz.pdp.userservice.jwt.JwtService;
import uz.pdp.userservice.repository.UserRepository;
import uz.pdp.userservice.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponce register(UserDto user) {
        UserEntity userEntity = mapper.map(user, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setIsDeleted(false);
        if (!checkPassword(user.getPassword())) {
            throw new CustomException("Parol minimum 5 ta va maksimum 50 ta belgi bo'lishi kerak");
        }
        if (!isUserExist(userEntity.getUsername())) {
            userRepository.save(userEntity);
            String jwtToken = jwtService.generateToken(userEntity);
            return AuthenticationResponce.builder().username(userEntity.getUsername()).password(userEntity.getPassword()).token(jwtToken).build();
        }

        throw new UserNotFoundException("User mavjud");
    }

    @Override
    public AuthenticationResponce login(AuthenticationRequest request) {
        UserEntity userEntity = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UserNotFoundException("User mavjud"));
        if (userEntity.getIsDeleted()) {
            throw new UserNotFoundException("User mavjud emas");
        }
        if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            throw new CustomException("Parol yoki username xato kiritildi!!! ");
        }

        String token = jwtService.generateToken(userEntity);
        return AuthenticationResponce.builder().username(userEntity.getUsername()).password(userEntity.getPassword()).token(token).build();


    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> all = userRepository.findAllActiveUsers();
        if (all.isEmpty()) {
            throw new CustomException("Ma'lumot topilmadi");
        }
        List<UserDto> userDtos = new ArrayList<>();
        for (UserEntity userEntity : all) {
            userDtos.add(mapper.map(userEntity, UserDto.class));
        }
        return userDtos;
    }

    @Override
    public boolean isUserExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public void deleteById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User mavjud emas"));
        if (userEntity.getIsDeleted()) {
            throw new UserNotFoundException("User mavjud emas");
        }
        userEntity.setIsDeleted(true);
        userRepository.save(userEntity);
    }

    @Override
    public UserDto getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User mavjud emas"));
        if (userEntity != null && !userEntity.getIsDeleted()) {
            return mapper.map(userEntity, UserDto.class);
        }
        throw new UserNotFoundException("User mavjud emas");
    }

    @Override
    public UserDto updateUser(UserDto user, Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User mavjud"));
        if (userEntity.getIsDeleted()) {
            throw new UserNotFoundException("User mavjud emas");
        }
        if (!checkPassword(user.getPassword())) {
            throw new CustomException("Parol minimum 5 ta va maksimum 50 ta belgi bo'lishi kerak");
        }
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntity);
        return mapper.map(userEntity, UserDto.class);

    }

    @Override
    public boolean checkPassword(String password) {
        if (password.length() >= 5 && password.length() <= 50) {
            return true;
        }
        return false;
    }
}
