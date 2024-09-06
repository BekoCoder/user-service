package uz.pdp.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import uz.pdp.userservice.entity.RoleEntity;

import java.util.Set;

@Data
@Schema(description = "User bo'yicha ma'lumotlar")
public class UserDto {

    @Schema(description = "Foydalanuvchi ismi")
    private String name;

    @Schema(description = "Foydalanuvchi username")
    private String username;

    @Schema(description = "Foydalanuvchi paroli")
    private String password;

    @Schema(description = "Foydalanuvchi roli")
    private Set<RoleEntity> roles;

}
