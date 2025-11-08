package com.achamigos.backend.controller;

import com.achamigos.backend.model.Endereco;
import com.achamigos.backend.model.User;
import com.achamigos.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CadastroController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/cadastro")
    public String mostrarFormulario() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(@RequestParam String nome,
                                   @RequestParam String cnpj,
                                   @RequestParam String telefone,
                                   @RequestParam String userLogin,
                                   @RequestParam String senha,
                                   @RequestParam String email,
                                   @RequestParam String cep,
                                   @RequestParam String rua,
                                   @RequestParam String cidade,
                                   @RequestParam String numero,
                                   Model model) {

        // Evita duplicidade de e-mail
        if (userRepository.findByEmail(email) != null) {
            model.addAttribute("error", "E-mail já cadastrado!");
            return "cadastro";
        }

        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setRua(rua);
        endereco.setCidade(cidade);
        endereco.setNumero(numero);

        User user = new User();
        user.setNome(nome);
        user.setCnpj(cnpj);
        user.setTelefone(telefone);
        user.setUserLogin(userLogin);
        user.setSenha(senha);
        user.setEmail(email);
        user.setEndereco(endereco);

        userRepository.save(user);

        model.addAttribute("success", "Usuário cadastrado com sucesso!");
        return "cadastro";
    }
}
