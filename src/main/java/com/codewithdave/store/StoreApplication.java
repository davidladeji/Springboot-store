package com.codewithdave.store;

// import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.codewithdave.store.entities.Address;
import com.codewithdave.store.entities.User;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		// ConfigurableApplicationContext context = SpringApplication.run(StoreApplication.class, args);
		var user = User.builder()
			.name("John")
			.email("email")
			.password("password")
			.build();

		var address = Address.builder()
			.street("street")
			.city("city")
			.state("state")
			.zip("zip")
			.build();

		user.addAddress(address);
		System.out.println(user);
	}

}
