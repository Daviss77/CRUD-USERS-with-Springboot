package com.javanauta.cadastro_usuario.business;

import com.javanauta.cadastro_usuario.infra.entitys.User;
import com.javanauta.cadastro_usuario.infra.entitys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.saveAndFlush(user);
    }

    public User searchUserByEmail(String email){

        return userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email not found!")
                );
    }

    public void deleteUserByEmail(String email){
        userRepository.deleteByEmail(email);
    }

    public void updateUserById(Integer id, User user){
        User userEntity =  userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        User userUpdate = User.builder()
                .email(user.getEmail() != null ? user.getEmail() : userEntity.getEmail())
                .name(user.getName() != null ? user.getName() : userEntity.getName())
                .id(userEntity.getId())
                .build();

        userRepository.saveAndFlush(userEntity);
    }
}
