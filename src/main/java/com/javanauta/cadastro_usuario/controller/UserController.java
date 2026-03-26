package com.javanauta.cadastro_usuario.controller;

import com.javanauta.cadastro_usuario.business.UserService;
import com.javanauta.cadastro_usuario.infra.entitys.User;
import com.javanauta.cadastro_usuario.infra.entitys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody User user){
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<User> searchUser(@RequestParam String email){
        return ResponseEntity.ok(userService.searchUserByEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestParam String email){
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody Integer id,
                                           @RequestBody User user){
        userService.updateUserById(id, user);
        return ResponseEntity.ok().build();

    }
}
