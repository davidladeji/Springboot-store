package com.codewithdave.store;
import org.springframework.beans.factory.annotation.Value;


public class EmailNotificationService implements NotificationService {
    
    @Value("${host}")
    private String host;
    @Value("${port}")
    private int port;


    @Override
    public void send(String message, String recipientEmail){
        System.out.println();
        System.out.println("Host: " + host + " & Port: " + port);
        System.out.println("Sending email to " + recipientEmail+ ": " + message);
    }
}
