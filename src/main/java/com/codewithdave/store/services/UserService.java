package com.codewithdave.store.services;

import org.springframework.stereotype.Service;

import com.codewithdave.store.entities.User;
import com.codewithdave.store.repositories.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    // This tag makes the persistent context open for the entire method as opposed to 
    // each transactional instruction
    @Transactional
    public void showEntityStates(){
        var user = User.builder()
            .name("John")
            .email("john@example.com")
            .password("password")
            .build();

        if (entityManager.contains(user)){
            System.out.println("Persistent");
        } else {
            System.out.println("Transient / Detached");
        }

        userRepository.save(user);

        if (entityManager.contains(user)){
            System.out.println("Persistent");
        } else {
            System.out.println("Transient / Detached");
        }
        
    }
}
