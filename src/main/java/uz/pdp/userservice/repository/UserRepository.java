package uz.pdp.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.userservice.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select u from UserEntity u where u.username=:username")
    Optional<UserEntity> findByUsername(String username);

    @Query("select u from UserEntity u where u.isDeleted=false")
    List<UserEntity> findAllActiveUsers();

}
