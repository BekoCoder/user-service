package uz.pdp.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.userservice.entity.PermissionEntity;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    @Query(value = "select p from PermissionEntity p where p.name=:name")
    Optional<PermissionEntity> findByName(String name);

}
