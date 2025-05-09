package com.codewithdave.store.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.codewithdave.store.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
    @EntityGraph(attributePaths = {"tags", "addresses"})
    Optional<User> findByEmail(String email);
}
