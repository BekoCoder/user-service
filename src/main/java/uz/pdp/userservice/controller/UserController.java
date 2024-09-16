package uz.pdp.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.userservice.service.UserService;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @Operation(summary = "Foydalanuvchining Roleni olish")
    @GetMapping("/role")
    public ResponseEntity<String> getRole(Principal principal) {
        Set<String> roles = userService.getRoleByUsername(principal.getName());
        System.out.println(roles.toString());
        if (roles.contains("SUPER_ADMIN")) {
            return ResponseEntity.ok("SUPER_ADMIN");
        } else if (roles.contains("ADMIN")) {
            return ResponseEntity.ok("ADMIN");
        } else {
            return ResponseEntity.ok("USER");
        }
    }
}
