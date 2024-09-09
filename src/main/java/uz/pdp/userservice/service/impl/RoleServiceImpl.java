package uz.pdp.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.userservice.dto.RoleDto;
import uz.pdp.userservice.entity.RoleEntity;
import uz.pdp.userservice.entity.UserEntity;
import uz.pdp.userservice.exception.CustomException;
import uz.pdp.userservice.repository.RoleRepository;
import uz.pdp.userservice.repository.UserRepository;
import uz.pdp.userservice.service.RoleService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    @Override
    public RoleEntity addRole(RoleDto role) {
        RoleEntity roleEntity = mapper.map(role, RoleEntity.class);
        if (isExistRole(roleEntity.getName())) {
            throw new CustomException("Bunday Role oldin yaratilgan");
        }
        return roleRepository.save(roleEntity);
    }

    @Override
    public RoleEntity getById(Long id) {
        RoleEntity roleEntity = roleRepository.findById(id).orElseThrow(() -> new CustomException("Bunday role mavjud emas"));
        if (roleEntity == null) {
            throw new CustomException("Bunday Role mavjud emas");
        }
        return roleEntity;
    }

    @Override
    public RoleEntity updateRole(Long id, RoleDto role) {
        RoleEntity roleEntity = roleRepository.findById(id).orElseThrow(() -> new CustomException("Bunday Role yaratilgan"));
        roleEntity.setName(role.getName());
        return roleRepository.save(roleEntity);

    }

    @Override
    public void deleteRole(Long id) {
        RoleEntity roleEntity = roleRepository.findById(id).orElseThrow(() -> new CustomException("Role mavjud emas"));
        roleRepository.delete(roleEntity);
    }

    private boolean isExistRole(String roleName) {
        return roleRepository.findByName(roleName).isPresent();
    }

    @Override
    public Optional<RoleEntity> assignRoleToUser(Long userId, Long roleId) {
        Optional<RoleEntity> byId = roleRepository.findById(roleId);
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new CustomException("User mavjud emas"));
        if (userEntity.getIsDeleted()) {
            throw new CustomException("User o'chirilgan");
        } else if (byId.isPresent()) {
            RoleEntity roleEntity = byId.get();
            userEntity.getRoles().add(roleEntity);
            userRepository.save(userEntity);
            roleRepository.save(roleEntity);
            return Optional.of(roleEntity);
        } else {
            throw new CustomException("Bunday Role yaratilmagan");
        }
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }
}
