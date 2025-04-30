package com.codewithdave.store;

// import org.springframework.stereotype.Service;

// @Service("paypal")
public class PaypalPaymentService implements PaymentService {

    @Override
    public void processPayment(double amount){
        System.out.println("PAYPAL");
        System.out.println("Amount: " + amount);
    }
}
