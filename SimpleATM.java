import java.util.InputMismatchException;
import java.util.Scanner;

class Account {
    private double balance;

    public Account(double startBalance) {
        this.balance = Math.max(0, startBalance); 
    }


    public double checkFunds() {
        return this.balance;
    }

    
    public boolean addFunds(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false; 
    }


    public boolean removeFunds(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            return true; 
        }
        return false; 
    }
}


class TransactionHandler {
    private Account userAccount;
    private Scanner inputReader;

    public TransactionHandler(Account account, Scanner scanner) {
        this.userAccount = account;
        this.inputReader = scanner;
    }


    public void performCheckBalance() {
        System.out.printf("Current account balance: $%.2f\n", userAccount.checkFunds());
    }


    public void performDeposit() {
        System.out.print("Enter deposit amount: $");
        try {
            double amount = inputReader.nextDouble();
            if (userAccount.addFunds(amount)) {
                System.out.println("Deposit accepted.");
                performCheckBalance(); 
            } else {
                System.out.println("Deposit failed. Amount must be positive.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numerical amount.");
            inputReader.next(); 
        }
    }

 
    public void performWithdrawal() {
        System.out.print("Enter withdrawal amount: $");
         try {
            double amount = inputReader.nextDouble();
            if (userAccount.removeFunds(amount)) {
                System.out.println("Withdrawal successful.");
                performCheckBalance(); 
            } else {
                
                if (amount <= 0) {
                     System.out.println("Withdrawal failed. Amount must be positive.");
                } else {
                     System.out.println("Withdrawal failed. Insufficient funds.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numerical amount.");
            inputReader.next(); 
        }
    }
}



public class SimpleATM {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account account = new Account(500.00); 
        TransactionHandler handler = new TransactionHandler(account, scanner);
        boolean keepRunning = true;

        System.out.println("Welcome to Command Style ATM");

        while (keepRunning) {
            System.out.println("\nOptions:");
            System.out.println(" B - Check Balance");
            System.out.println(" D - Deposit");
            System.out.println(" W - Withdraw");
            System.out.println(" E - Exit");
            System.out.print("Enter option (B/D/W/E): ");

            String choice = scanner.next().toUpperCase(); 

            switch (choice) {
                case "B":
                    handler.performCheckBalance();
                    break;
                case "D":
                    handler.performDeposit();
                    break;
                case "W":
                    handler.performWithdrawal();
                    break;
                case "E":
                    System.out.println("Exiting ATM. Thank you!");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid option selected. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
