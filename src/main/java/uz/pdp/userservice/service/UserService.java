package uz.pdp.userservice.service;

import uz.pdp.userservice.dto.AuthenticationRequest;
import uz.pdp.userservice.dto.AuthenticationResponce;
import uz.pdp.userservice.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface UserService {
    AuthenticationResponce register(UserDto user);

    AuthenticationResponce login(AuthenticationRequest request);

    List<UserDto> getAllUsers();

    boolean isUserExist(String username);

    void deleteById(Long id);

    UserDto getUserById(Long id);

    UserDto updateUser(UserDto user, Long id);

    boolean checkPassword(String password);

    Set<String> getRoleByUsername(String username);

}
