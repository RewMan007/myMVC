package mvc;
import javax.swing.*;

import Controller.BankController;
import Model.AccountModel;
import Views.BankView;

public class Main {
    public static void main(String[] args) {
        // Initialize sample bank accounts
        AccountModel.initializeAccounts();

        // Prompt the user for account number and password
        String accountNumber = JOptionPane.showInputDialog("Enter account number:");
        String password = JOptionPane.showInputDialog("Enter password:");

        // Remove '-' if present in the account number
        accountNumber = accountNumber.replaceAll("-", "");

        // Authenticate the user's account based on account number and password
        AccountModel account = AccountModel.authenticate(accountNumber, password);
        if (account != null) {
            // If authentication is successful, proceed to the main application

            // Create the bank view (GUI)
            BankView theView = new BankView();

            // Create the bank controller and link it with the account and view
            BankController theController = new BankController(account, theView);

            // Make the GUI visible to the user
            theView.setVisible(true);
        } else {
            // If authentication fails, show an error message
            JOptionPane.showMessageDialog(null, "Incorrect account number or password.");
        }
    }
}
