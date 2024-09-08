package uz.pdp.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Ruxsatlar bo'yicha ma'lumotlar")
public class PermissionDto {
    @Schema(description = "Nomi")
    private String name;
}
