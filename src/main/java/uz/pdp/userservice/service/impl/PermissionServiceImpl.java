package uz.pdp.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.userservice.dto.PermissionDto;
import uz.pdp.userservice.entity.PermissionEntity;
import uz.pdp.userservice.entity.RoleEntity;
import uz.pdp.userservice.exception.CustomException;
import uz.pdp.userservice.exception.PermissionNotFoundException;
import uz.pdp.userservice.exception.RoleNotFoundException;
import uz.pdp.userservice.repository.PermissionRepository;
import uz.pdp.userservice.repository.RoleRepository;
import uz.pdp.userservice.service.PermissionService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;


    @Override
    public PermissionEntity addPermission(PermissionDto permissionDto) {
        PermissionEntity permission = mapper.map(permissionDto, PermissionEntity.class);
        if (isExistPermission(permissionDto.getName())) {
            throw new PermissionNotFoundException("Bunday Permission oldin yaratilgan");
        }
        return permissionRepository.save(permission);
    }

    @Override
    public PermissionEntity getPermissionById(Long id) {
        PermissionEntity permission = permissionRepository.findById(id).orElseThrow(() -> new PermissionNotFoundException("Bunday Permission mavjud emas"));
        if (permission == null) {
            throw new PermissionNotFoundException("Bunday Permission yaratilgan");
        }
        return permission;

    }

    @Override
    public void deletePermissionById(Long id) {
        PermissionEntity permission = permissionRepository.findById(id).orElseThrow(() -> new PermissionNotFoundException("Bunday Permission yaratilgan"));
        permissionRepository.delete(permission);
    }

    @Override
    public List<PermissionEntity> getPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public PermissionEntity updatePermission( Long id,PermissionDto permissionDto) {
        PermissionEntity permission = permissionRepository.findById(id).orElseThrow(() -> new PermissionNotFoundException("Bunday Permission yaratilgan"));
        if(Objects.equals(permission.getId(), id)){
           permission.setName(permissionDto.getName());
           return permissionRepository.save(permission);
        }
        throw new CustomException("Bunday Permission Topilmadi");
    }

    private boolean isExistPermission(String permissionName) {
        return permissionRepository.findByName(permissionName).isPresent();
    }

    @Override
    public Optional<PermissionEntity> assignPermissionToRole(Long roleId, Long permissionId) {
        Optional<PermissionEntity> byId = permissionRepository.findById(permissionId);
        RoleEntity roleEntity = roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Bunday Role yaratilgan"));
        if(byId.isPresent()){
            PermissionEntity permission = byId.get();
            roleEntity.getPermissions().add(permission);
            roleRepository.save(roleEntity);
            permissionRepository.save(permission);
            return Optional.of(permission);
        }
    else {
        throw new PermissionNotFoundException("Bunday Permission yaratilgan");
        }

    }
}
