package uz.pdp.userservice.service;

import uz.pdp.userservice.dto.RoleDto;
import uz.pdp.userservice.entity.RoleEntity;

import java.util.Optional;

public interface RoleService {

    RoleEntity addRole(RoleDto role);

    RoleEntity getById(Long id);

    RoleEntity updateRole(Long id, RoleDto role);

    void deleteRole(Long id);

    Optional<RoleEntity> assignRoleToUser(Long userId, Long roleId);

}
