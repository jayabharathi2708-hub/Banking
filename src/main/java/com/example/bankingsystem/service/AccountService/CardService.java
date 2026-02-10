package com.example.bankingsystem.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    public String checkEligibility(int age, double income, int cibil) {
        if (age >= 21 && age <= 60 && income >= 25000 && cibil >= 750) {
            return "Eligible for IDFC Millennia, Federal Scapia, HSBC Platinum";
        }
        return "Not eligible. Improve CIBIL & Income.";
    }

    public String calculateRewards(double spend) {
        int basePts = (int)(20000 / 150 * 3);
        int extraPts = (int)(Math.max(0, spend - 20000) / 150 * 10);
        int totalPts = basePts + extraPts;

        return "Base Points: " + basePts +
               ", Extra Points: " + extraPts +
               ", Total Points: " + totalPts +
               ", Value ₹" + (totalPts * 0.25);
    }
}

