package com.codewithdave.store.services;

import org.springframework.stereotype.Service;

import com.codewithdave.store.entities.Profile;
import com.codewithdave.store.entities.User;
import com.codewithdave.store.repositories.ProfileRepository;
import com.codewithdave.store.repositories.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EntityManager entityManager;

    // This tag makes the persistent context open for the entire method as opposed to 
    // each transactional instruction
    @Transactional
    public void showEntityStates(){
        var user = User.builder()
            .name("John Doe")
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

    // Trying to access the user when the 1-to-1 mapping is lazy loading will throw an error
    // because the entity is detached so we aren't able to read this relationship from the db
    @Transactional
    public void showRelatedEntities(){
        var profile = profileRepository.findById(2L).orElseThrow();
        // var user = profile.getUser();
        // System.out.println(user.getName());
        System.out.println(profile.getBio());
    }
}
