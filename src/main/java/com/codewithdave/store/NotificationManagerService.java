package com.codewithdave.store;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationManagerService {
    @SuppressWarnings("unused")
    private NotificationService notificationService;

    public NotificationManagerService(@Qualifier("sms") NotificationService notificationService){
        this.notificationService = notificationService;
    }

    public void sendNotification(String message){
        // ** Reused this code for another assignment. Didn't want to update legacy code **
        // notificationService.send(message);
    }
}
