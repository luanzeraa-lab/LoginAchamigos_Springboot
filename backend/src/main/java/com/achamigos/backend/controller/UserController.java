package com.achamigos.backend.controller;

import com.achamigos.backend.model.User;
import com.achamigos.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {
        "http://localhost:3000"
}, allowCredentials = "true")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<User> listarTodos() {
        return userRepository.findAll();
    }


    @GetMapping("/{id}")
    public Optional<User> buscarPorId(@PathVariable String id) {
        return userRepository.findById(id);
    }


    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        User user = userRepository.findByUserLogin(loginRequest.getUserLogin());

        if (user == null) {
            return "Usuário não encontrado!";
        }

        boolean senhaOk = BCrypt.checkpw(loginRequest.getSenha(), user.getSenha());
        if (!senhaOk) {
            return "Senha incorreta!";
        }

        return "Login bem-sucedido para: " + user.getNome();
    }
}
