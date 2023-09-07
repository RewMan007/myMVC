package Model;
import java.util.Map;
import java.util.HashMap;

public class AccountModel {
    private double balance;
    private String password;  // New field to store the password
    private static Map<String, AccountModel> accounts = new HashMap<>();  // Simple database

    public AccountModel(double balance, String password) {
        this.balance = balance;
        this.password = password;
    }

    public static void initializeAccounts() {
        // Initialize sample accounts with balances and passwords
        accounts.put("2562342110001", new AccountModel(35285.53, "6969"));
        accounts.put("2562342110002", new AccountModel(103478.98, "4117"));
        accounts.put("2562342110003", new AccountModel(1781.25, "8143"));
    }

    // Authenticate a user based on their account number and password
    public static AccountModel authenticate(String accountNumber, String enteredPassword) {
        AccountModel account = accounts.get(accountNumber);
        if (account != null && account.password.equals(enteredPassword)) {
            return account;
        }
        return null;
    }

    // Get an account based on its account number
    public static AccountModel getAccount(String accountNumber) {
        accountNumber = accountNumber.replaceAll("-", "");  // Remove '-' if present
        return accounts.get(accountNumber);
    }

    // Deposit money into the account
    public int deposit(double amount) {
        if (amount < 100) {
            return 1; // Amount must be at least 100
        }
        if (!isMultipleOfNotes(amount, new double[]{1000, 500, 100})) {
            return 2; // Amount must be made up of 1,000, 500, or 100 Baht notes
        }
        balance += amount;
        return 0; // Success
    }

    // Withdraw money from the account
    public int withdraw(double amount) {
        if (amount > balance) {
            return 1; // Amount cannot exceed the balance
        }
        if (amount > 20000) {
            return 2; // Amount cannot exceed the 20,000 Baht limit
        }
        if (!isMultipleOfNotes(amount, new double[]{1000, 500, 100})) {
            return 3; // Amount must be made up of 1,000, 500, or 100 Baht notes
        }
        balance -= amount;
        return 0; // Success
    }

    // Check if the given amount can be represented using specific banknote denominations
    private boolean isMultipleOfNotes(double amount, double[] notes) {
        for (double note : notes) {
            if (amount >= note) {
                amount -= note * (Math.floor(amount / note));
            }
        }
        return amount == 0;
    }

    // Transfer money from the current account to another account
    public boolean transfer(double amount, String toAccountNumber) {
        if (amount <= 1000000) {
            AccountModel toAccount = getAccount(toAccountNumber);
            if (toAccount != null) {
                if (amount <= balance) {
                    balance -= amount;
                    toAccount.balance += amount;
                    return true;
                }
            }
        }
        return false;
    }

    // Get the current balance of the account
    public double getBalance() {
        return balance;
    }
}
