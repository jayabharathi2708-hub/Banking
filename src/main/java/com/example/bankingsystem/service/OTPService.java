package com.example.bankingsystem.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OTPService {

    private Map<String, String> otpMap = new HashMap<>();

    // Generate OTP
    public String generateOtp(String username) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        otpMap.put(username, otp);
        return otp;
    }

    // Verify OTP
    public boolean verifyOtp(String username, String otp) {
        return otp.equals(otpMap.get(username));
    }
}
