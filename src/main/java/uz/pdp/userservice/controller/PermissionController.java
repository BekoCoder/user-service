package uz.pdp.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.userservice.dto.PermissionDto;
import uz.pdp.userservice.dto.RoleDto;
import uz.pdp.userservice.entity.PermissionEntity;
import uz.pdp.userservice.service.PermissionService;

import java.util.List;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@Tag(name = "permission-controller", description = "Ruxsatlar uchun controller")
public class PermissionController {
    private final PermissionService permissionService;

    @Operation(summary = "Ruxsat yaratish")
    @PostMapping("/create")
    public ResponseEntity<PermissionEntity> createPermission(@RequestBody PermissionDto permission) {
        return ResponseEntity.ok(permissionService.addPermission(permission));
    }

    @Operation(summary = "Ruxsatlarni id orqali olish")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<PermissionEntity> getPermissionById(@PathVariable Long id) {
        return ResponseEntity.ok(permissionService.getPermissionById(id));
    }

    @Operation(summary = "Ruxsatlarni Id orqali o'chirish")
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<?> deletePermissionById(@PathVariable Long id) {
        permissionService.deletePermissionById(id);
        return ResponseEntity.ok(true);
    }

    @Operation(summary = "Permissionlarni yangilash")
    @PutMapping("/update-permission/{id}")
    public ResponseEntity<PermissionEntity> updatePermission(@PathVariable Long id, @RequestBody PermissionDto permission) {
        return ResponseEntity.ok(permissionService.updatePermission(id, permission));
    }

    @Operation(summary = "Ruxsatlarni Role ga biriktirish")
    @PutMapping("/assign-permission-to-role/{roleId}/{permissionId}")
    public ResponseEntity<?> assignPermissionToRole(@PathVariable Long roleId, @PathVariable Long permissionId) {
        return ResponseEntity.ok(permissionService.assignPermissionToRole(roleId, permissionId));
    }

    @Operation(summary = "Ruxsatlarni barchasini olish")
    @GetMapping("/get-permissions")
    public ResponseEntity<List<PermissionEntity>> getPermissions() {
       return ResponseEntity.ok(permissionService.getPermissions());
    }


}
