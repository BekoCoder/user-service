package uz.pdp.userservice.service;

import uz.pdp.userservice.dto.PermissionDto;
import uz.pdp.userservice.entity.PermissionEntity;

import java.util.List;
import java.util.Optional;

public interface PermissionService {
    PermissionEntity addPermission(PermissionDto permissionDto);

    PermissionEntity getPermissionById(Long id);

    void deletePermissionById(Long id);

    List<PermissionEntity> getPermissions();

    PermissionEntity updatePermission(Long id, PermissionDto permissionDto);

    Optional<PermissionEntity> assignPermissionToRole(Long roleId, Long permissionId);
}
