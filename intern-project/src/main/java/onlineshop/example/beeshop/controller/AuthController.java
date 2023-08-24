package onlineshop.example.beeshop.controller;

import onlineshop.example.beeshop.dto.LoginRequestDTO;
import onlineshop.example.beeshop.dto.LoginResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    private LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

        return null;
    }
}
