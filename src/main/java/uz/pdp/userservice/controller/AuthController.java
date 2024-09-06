package uz.pdp.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.userservice.dto.AuthenticationRequest;
import uz.pdp.userservice.dto.AuthenticationResponce;
import uz.pdp.userservice.dto.UserDto;
import uz.pdp.userservice.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "auth-controller", description = "Foydalanuvchilarni login va register uchun apilar")
@Slf4j
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Foydalanuvchilarni ro'yhatdan o'tkazish uchun API")
    public ResponseEntity<AuthenticationResponce> register(@RequestBody UserDto userDto) {
        log.trace("Register user: {}", userDto);
        return ResponseEntity.ok(userService.register(userDto));
    }

    @PostMapping("/login")
    @Operation(summary = "Foydalanuchilarni login qilish uchun API")
    public ResponseEntity<AuthenticationResponce> login(@RequestBody AuthenticationRequest authenticationRequest) {
        log.trace("Login user: {}", authenticationRequest);
        return ResponseEntity.ok(userService.login(authenticationRequest));
    }
}
