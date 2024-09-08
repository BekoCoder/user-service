package uz.pdp.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.userservice.dto.RoleDto;
import uz.pdp.userservice.entity.RoleEntity;
import uz.pdp.userservice.service.RoleService;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@Slf4j
public class RoleController {
    private final RoleService roleService;

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
