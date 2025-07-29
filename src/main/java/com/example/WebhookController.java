package com.example;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class WebhookController {
    
    @PostMapping("/api/hiring/generatewebhook")
    public Map<String, String> generateWebhook(@RequestBody Map<String, String> request) {
        
        String name = request.get("name");
        String email = request.get("email");
        String topic = request.get("topic");
        
        if (name == null || name.trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "name is required");
            return error;
        }
        
        if (email == null || email.trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "email is required");
            return error;
        }
        
        String webhookId = UUID.randomUUID().toString().substring(0, 8);
        String accessToken = "BRH_" + UUID.randomUUID().toString().replace("-", "").substring(0, 12);
        String webhookUrl = "https://brillerapipe.healthlane.co.in/hiring/testwebhook/" + webhookId;
        
        Map<String, String> response = new HashMap<>();
        response.put("webhookUrl", webhookUrl);
        response.put("accessToken", accessToken);
        
        System.out.println("webhook for: " + name + " (" + email + ")");
        
        return response;
    }
    
    @PostMapping("/api/testwebhook")
    public Map<String, String> testWebhook(
            @RequestBody String payload,
            @RequestHeader(value = "Authorization", required = false) String authToken) {
        
        System.out.println("received webhook payload: " + payload);
        System.out.println(" auth token: " + authToken);
        
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "success");
        
        return response;
    }
    
    @GetMapping("/health")
    public Map<String, String> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("message", "Server is running!");
        return response;
    }
}
