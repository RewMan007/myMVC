package Controller;
import Model.AccountModel;
import Views.BankView;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.*;
import java.awt.event.*;

public class BankController {
    private AccountModel account;
    private BankView view;

    // Constructor for the BankController class
    public BankController(AccountModel account, BankView view) {
        this.account = account;
        this.view = view;

        // Add action listeners to buttons in the view
        this.view.getDepositButton().addActionListener(new DepositListener());
        this.view.getWithdrawButton().addActionListener(new WithdrawListener());
        this.view.getTransferButton().addActionListener(new TransferListener());
        this.view.getCheckBalanceButton().addActionListener(new CheckBalanceListener());
    }

    // ActionListener for the Deposit button
    class DepositListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Prompt the user to enter the deposit amount
            String input = JOptionPane.showInputDialog("Enter amount to deposit:");
            double amount = Double.parseDouble(input);

            // Perform the deposit operation and show appropriate messages
            int result = account.deposit(amount);
            if (result == 0) {
                JOptionPane.showMessageDialog(null, "Deposited: " + amount + " New balance: " + account.getBalance());
            } else if (result == 1) {
                JOptionPane.showMessageDialog(null, "Minimum deposit amount is 100 Baht.");
            } else if (result == 2) {
                JOptionPane.showMessageDialog(null, "Invalid deposit amount. Only 1,000, 500, and 100 Baht notes are allowed.");
            }
        }
    }

    // ActionListener for the Withdraw button
    class WithdrawListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Prompt the user to enter the withdrawal amount
            String input = JOptionPane.showInputDialog("Enter amount to withdraw:");
            double amount = Double.parseDouble(input);

            // Perform the withdrawal operation and show appropriate messages
            int result = account.withdraw(amount);
            if (result == 0) {
                JOptionPane.showMessageDialog(null, "Withdrew: " + amount + " New balance: " + account.getBalance());
            } else if (result == 1) {
                JOptionPane.showMessageDialog(null, "Withdraw amount exceeds your current balance.");
            } else if (result == 2) {
                JOptionPane.showMessageDialog(null, "Maximum withdraw limit is 20,000 Baht.");
            } else if (result == 3) {
                JOptionPane.showMessageDialog(null, "Invalid withdraw amount. Only 1,000, 500, and 100 Baht notes are allowed.");
            }
        }
    }

    // ActionListener for the Transfer button
    class TransferListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Prompt the user to enter the transfer amount and destination account number
            String amountStr = JOptionPane.showInputDialog("Enter amount to transfer:");
            double amount = Double.parseDouble(amountStr);
            String toAccountNumber = JOptionPane.showInputDialog("Enter account number to transfer to:");

            // Perform the transfer operation and show appropriate messages
            if (account.transfer(amount, toAccountNumber)) {
                JOptionPane.showMessageDialog(null, "Transferred: " + amount + " New balance: " + account.getBalance());
            } else {
                JOptionPane.showMessageDialog(null, "Transfer failed. Insufficient balance, or invalid account number, or amount greater than 1,000,000.");
            }
        }
    }

    // ActionListener for the Check Balance button
    class CheckBalanceListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Display the current account balance to the user
            JOptionPane.showMessageDialog(null, "Current balance: " + account.getBalance());
        }
    }
}
