package uz.pdp.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Role lar bo'yicha ma'lumotlar")
public class RoleDto {
    private String name;
}
