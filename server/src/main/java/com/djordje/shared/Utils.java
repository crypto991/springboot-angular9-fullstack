package com.djordje.shared;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPRSTUVWXYZabcdefghijklmnoprstuvwxyz";

    
    
    public String generateUserId(int lenght) {
        return generateRandomString(lenght);
    }
    public String generateAccountId(int lenght) {
        return generateRandomString(lenght);
    }
    public String generateFarmId(int lenght) {
        return generateRandomString(lenght);
    }
    public String generateCustomerId(int lenght) {
        return generateRandomString(lenght);
    }


    
    private String generateRandomString(int lenght) {
        StringBuilder returnValue = new StringBuilder(lenght);

        for (int i = 0; i < lenght; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }


}
