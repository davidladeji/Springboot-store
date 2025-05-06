package com.codewithdave.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.codewithdave.store.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
    
}
