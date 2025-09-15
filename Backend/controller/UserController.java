package com.exemplo.cadastro.controller;

import com.exemplo.cadastro.model.User;
import com.exemplo.cadastro.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User cadastrar(@RequestBody User user) {
        return userService.cadastrar(user);
    }

    @GetMapping
    public List<User> listar() {
        return userService.listar();
    }

    @GetMapping("/{id}")
    public User buscarPorId(@PathVariable Long id) {
        return userService.buscarPorId(id);
    }
}
