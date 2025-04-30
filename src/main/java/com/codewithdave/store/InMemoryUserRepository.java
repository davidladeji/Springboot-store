package com.codewithdave.store;

import java.util.HashMap;

public class InMemoryUserRepository implements UserRepository {
    private HashMap<String, User> memory;

    // Memory HashMap has not been initialized

    public InMemoryUserRepository(){
        this.memory = new HashMap<>();
    }

    @Override
    public void save(User user){
        memory.put(user.getEmail(), user);
        System.out.println("Sucessfully saved user " + memory.get(user.getEmail()).getName());
    }
}
