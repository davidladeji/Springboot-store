package com.codewithdave.store;

// import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(StoreApplication.class, args);
		
		// var orderService = context.getBean(OrderService.class);
		// orderService.placeOrder();

		// var userService = context.getBean(UserService.class);
		// User user = new User(0, "test@gmail.com", "secure", "David Ladeji");
		// userService.registerUser(user);
		
		// Closing the application is a lifesaver when dealing with database migration files
		context.close();

		// // Lazy init bean
		// context.getBean(HeavyResource.class);

		// Notification code (Assignment)
		// var notificationManager = context.getBean(NotificationManagerService.class);
		// notificationManager.sendNotification("Hello World");
	}

}
