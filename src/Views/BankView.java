package Views;

import javax.swing.*;
import java.awt.*;

public class BankView extends JFrame {
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;
    private JButton checkBalanceButton;

    // Constructor for the BankView class
    public BankView() {
        // Set up the JFrame
        setTitle("Bank App");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create buttons for different bank operations
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        transferButton = new JButton("Transfer");
        checkBalanceButton = new JButton("Check Balance");

        // Add buttons to the JFrame
        add(depositButton);
        add(withdrawButton);
        add(transferButton);
        add(checkBalanceButton);
    }

    // Getter method for the Deposit button
    public JButton getDepositButton() {
        return depositButton;
    }

    // Getter method for the Withdraw button
    public JButton getWithdrawButton() {
        return withdrawButton;
    }

    // Getter method for the Transfer button
    public JButton getTransferButton() {
        return transferButton;
    }

    // Getter method for the Check Balance button
    public JButton getCheckBalanceButton() {
        return checkBalanceButton;
    }
}
