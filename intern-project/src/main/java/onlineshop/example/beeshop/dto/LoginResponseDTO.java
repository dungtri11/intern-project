package onlineshop.example.beeshop.dto;

import lombok.*;
import onlineshop.example.beeshop.common.Role;

@Getter
@Setter
public class LoginResponseDTO {
    private String identifyKey;
    private Role role;
}
