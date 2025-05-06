package com.codewithdave.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.codewithdave.store.entities.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    
}
