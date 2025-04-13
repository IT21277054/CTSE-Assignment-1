package com.ctse.userManagement.Services;

import com.postmarkapp.postmark.Postmark;
import com.postmarkapp.postmark.client.ApiClient;
import com.postmarkapp.postmark.client.data.model.message.Message;
import com.postmarkapp.postmark.client.data.model.message.MessageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final ApiClient client;
    
    @Value("${postmark.from.email}")
    private String fromEmail;

    public EmailService() {
        this.client = Postmark.getApiClient("e5483f70-2d3d-4d6d-bac6-c4bfe082b865");
    }

    public void sendOtpEmail(String toEmail, String otp) {
        Message message = new Message(
            fromEmail,
            toEmail,
            "Password Reset OTP",
            "Your OTP for password reset is: " + otp + "\nThis OTP will expire in 5 minutes."
        );

        try {
            MessageResponse response = client.deliverMessage(message);
            if (response.getErrorCode() != 0) {
                throw new RuntimeException("Failed to send email: " + response.getMessage());
            }
            System.out.println("Email sent successfully. Message ID: " + response.getMessageId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email", e);
        }

    }
} 