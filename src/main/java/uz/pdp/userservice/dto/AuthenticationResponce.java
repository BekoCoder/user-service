package uz.pdp.userservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponce {
    private String token;
    private String username;
    private String password;
}
