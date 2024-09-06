package uz.pdp.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.userservice.dto.UserDto;
import uz.pdp.userservice.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "admin-controller", description = "Admin vazifalari")
@Slf4j
public class AdminController {
    private final UserService userService;

    @Operation(summary = "Barcha foydalanuvchilarni olish")
    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.of(Optional.ofNullable(userService.getAllUsers()));
    }

    @Operation(summary = "Foydalanuvchini id orqali olish")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        log.info("get-by-id: " + id);
        return ResponseEntity.of(Optional.ofNullable(userService.getUserById(id)));
    }

    @Operation(summary = "Foydalanuvchini update qilish")
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        log.info("update-user: " + userDto);
        return ResponseEntity.ok(userService.updateUser(userDto, id));
    }
}
