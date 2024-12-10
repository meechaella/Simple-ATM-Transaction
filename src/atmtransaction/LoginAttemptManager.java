package atmtransaction;

import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;

public class LoginAttemptManager {
    
    //declare a hashmap to store the number of failed login attempts per account
    private final HashMap<String, Integer> loginAttempts = new HashMap<>();
    private final int max_attempts = 3; 
    
    //increments the failed login attempts for an account
    public void incrementAttempts(String accountNumber, Statement st) throws SQLException {
        //get current number of failed attempts
        int attempts = loginAttempts.getOrDefault(accountNumber, 0);
        attempts++; //increment attempts
        loginAttempts.put(accountNumber, attempts); //update the map
        
        //lock the account in the database if attempts exceed the allowed maximum
        if(attempts > max_attempts) {
            String lockAccount = "UPDATE account SET locked_at = NOW() WHERE account_number = '" + accountNumber + "'";
            st.executeUpdate(lockAccount);
        }
    }
    
    //checks if an account is locked by comparing the lock time and the current time
    public boolean isAccountLocked(String accountNumber, String lockedAt) {
        //check if the account has a locked timestamp
        if(lockedAt != null) {
            LocalDateTime lockedTime = LocalDateTime.parse(lockedAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime unlockTime = lockedTime.plusHours(24); //account unlocks after 24 hours
            return LocalDateTime.now().isBefore(unlockTime); //return true if still locked
        }
        //if no lock time exists, check max attempts
        return loginAttempts.getOrDefault(accountNumber, 0) >= max_attempts;
    }

    //reset login attempts count for an account
    public void resetAttempts(String accountNumber) {
        loginAttempts.put(accountNumber, 0);
    }
}