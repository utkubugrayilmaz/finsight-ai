package com.finsight.api.service;


import com.finsight.api.entity.User;
import com.finsight.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //Tüm kullanıcıları getir
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email.toLowerCase());
    }

    //Yeni kullanıcı oluşturma

    public User createUser(User user){
        //emaili küçük harfe çevir
        user.setEmail(user.getEmail().toLowerCase());
        //email var mı yok mu kontrol et

        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Bu email zaten kayıtlı: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    //Kullanıcı güncelleme
    public User updateUser(Long id, User updateUser){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + id));

        existingUser.setFullName(updateUser.getFullName());
        existingUser.setRole((updateUser.getRole()));

        return userRepository.save(existingUser);
    }

    //Son giriş zamanını güncelle

    public void updateLastLogin(Long id){
        userRepository.findById(id).ifPresent(user -> {
            user.setLastLoginAt(LocalDateTime.now());
            userRepository.save(user);
        });
    }

    //kullanıcı sil

    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new RuntimeException("Kullanıcı bulunamadı: " + id);
        }

        userRepository.deleteById(id);
    }

    //Kullanıcı sayısı getirsin

    public long getUserCount(){
        return userRepository.count();
    }

}
