package com.achamigos.backend.controller;

import com.achamigos.backend.model.ApiResponse;
import com.achamigos.backend.model.User;
import com.achamigos.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(
    origins = {
        "http://localhost:3000",
        "https://achamigos-full-stack-p6dr.onrender.com"
    },
    allowedHeaders = "*",
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}
)
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ApiResponse login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail());

        if (user != null && passwordEncoder.matches(request.getSenha(), user.getSenha())) {
            return new ApiResponse("success", "Login efetuado com sucesso!", user);
        } else {
            return new ApiResponse("error", "E-mail ou senha incorretos!", null);
        }
    }

    // Opcional: GET para teste se a rota está ativa
    @GetMapping
    public String teste() {
        return "API login ativa";
    }

    public static class LoginRequest {
        private String email;
        private String senha;

        // Getters e Setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }
}
