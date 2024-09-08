package uz.pdp.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.userservice.dto.RoleDto;
import uz.pdp.userservice.dto.UserDto;
import uz.pdp.userservice.entity.RoleEntity;
import uz.pdp.userservice.service.RoleService;
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
    private final RoleService roleService;

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

    @Operation(summary = "Role yaratish")
    @PostMapping("/createRole")
    public ResponseEntity<RoleEntity> createRole(@RequestBody RoleDto roleDto) {
        log.info("create-role: " + roleDto);
        return ResponseEntity.ok(roleService.addRole(roleDto));
    }

    @Operation(summary = "Roleni yangilash")
    @PutMapping("/update-role/{id}")
    public ResponseEntity<RoleEntity> updateRole(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        log.info("update-role: " + roleDto);
        return ResponseEntity.ok(roleService.updateRole(id, roleDto));
    }

    @Operation(summary = "Roleni Id orqali o'chirish")
    @DeleteMapping("/delete-id/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        log.info("delete-id: " + id);
        roleService.deleteRole(id);
        return ResponseEntity.ok(true);
    }

    @Operation(summary = "Roleni Id orqali olish")
    @GetMapping("/get-by-role-id/{id}")
    public ResponseEntity<RoleEntity> getRoleById(@PathVariable Long id) {
        log.info("get-by-role-id: " + id);
        return ResponseEntity.ok(roleService.getById(id));
    }

    @Operation(summary = "Userga role biriktirish")
    @PutMapping("/assignRoleToUser/{roleId}/{userId}")
    public ResponseEntity<?> assignRoleToUser(@PathVariable Long roleId, @PathVariable Long userId) {
        log.info("assign-role-to-user: " + userId);
        return ResponseEntity.ok(roleService.assignRoleToUser(userId, roleId));
    }


}
