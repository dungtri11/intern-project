package onlineshop.example.beeshop.controller;

import onlineshop.example.beeshop.dto.LoginRequestDTO;
import onlineshop.example.beeshop.dto.LoginResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/login")
    private LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

        return null;
    }
}
