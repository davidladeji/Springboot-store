package com.codewithdave.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${payment-gateway:stripe}")
    private String paymentGateway;

    @Bean
    public PaymentService stripe(){
        return new StripePaymentService();
    }

    @Bean
    public PaymentService paypal(){
        return new PaypalPaymentService();
    }

    @Bean
    public OrderService orderService(){
        
        if (paymentGateway.equals("paypal"))
            return new OrderService(paypal());
        
        return new OrderService(stripe());
    }

    @Bean
    public UserRepository inmemory(){
        return new InMemoryUserRepository();
    }

    @Bean
    public NotificationService email(){
        return new EmailNotificationService();
    }

    @Bean
    public UserService userService(){
        return new UserService(email(), inmemory());
    }
}
