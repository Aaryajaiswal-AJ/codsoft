import javax.swing.JOptionPane;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance > 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            JOptionPane.showMessageDialog(null, "Successfully deposited: $" + amount);
        } else {
            JOptionPane.showMessageDialog(null, "Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            JOptionPane.showMessageDialog(null, "Successfully withdrew: $" + amount);
            return true;
        } else if (amount > balance) {
            JOptionPane.showMessageDialog(null, "Insufficient balance for this withdrawal.");
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Withdrawal amount must be positive.");
            return false;
        }
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        String[] options = {"Withdraw", "Deposit", "Check Balance", "Exit"};
        while (true) {
            int choice = JOptionPane.showOptionDialog(null, "ATM Menu", "ATM",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == -1 || choice == 3) {
                break; // Exit the loop
            }

            switch (choice) {
                case 0:
                    withdraw();
                    break;
                case 1:
                    deposit();
                    break;
                case 2:
                    checkBalance();
                    break;
            }
        }
    }

    private void withdraw() {
        String input = JOptionPane.showInputDialog("Enter amount to withdraw:");
        if (input != null) {
            try {
                double amount = Double.parseDouble(input);
                account.withdraw(amount);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a number.");
            }
        }
    }

    private void deposit() {
        String input = JOptionPane.showInputDialog("Enter amount to deposit:");
        if (input != null) {
            try {
                double amount = Double.parseDouble(input);
                account.deposit(amount);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a number.");
            }
        }
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(null, "Current balance: $" + account.getBalance());
    }
}

public class ATMApplication {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00); // Initial balance of $1000
        ATM atm = new ATM(account);
        atm.displayMenu();
    }
}
