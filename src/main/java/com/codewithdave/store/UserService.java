package com.codewithdave.store;

public class UserService {
    private NotificationService notificationService;
    private UserRepository userRepository;

    public UserService(NotificationService notificationService, UserRepository userRepository){
        this.notificationService = notificationService;
        this.userRepository = userRepository;
        System.out.println("Initialized User Service");
    }

    public void registerUser(User user){
        userRepository.save(user);
        notificationService.send("Congrats. You've been registered!", user.getEmail());
    }
}
