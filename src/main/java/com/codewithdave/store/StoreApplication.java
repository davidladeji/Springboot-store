package com.codewithdave.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.codewithdave.store.entities.User;
import com.codewithdave.store.repositories.UserRepository;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
		var repository = context.getBean(UserRepository.class);

		// Add user
		var user = User.builder()
			.name("John")
			.email("johnsmith@gmail.com")
			.password("password")
			.build();

		repository.save(user);

		// Get User
		var user2 = repository.findById(1L).orElse(null);
		// Printing the whole user gives a lazy load error fro some reason
    	System.out.println(user2.getEmail());
		

		// Delete User
		repository.deleteById(1L);
		
	}

}
