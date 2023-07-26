import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private int accountNumber;
    private String pin;
    private double balance;

    static int accountCounter;

    public Account() {
        Account.accountCounter+=1;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Account Number of account number "+accountCounter+" :");
        int accountNumber=sc.nextInt();
        System.out.println("Enter the PIN: ");
        String pin=sc.next();
        System.out.println("Enter Initial balance: ");
        double balance=sc.nextDouble();
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;

    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}

class ATM {
    private ArrayList<Account> accounts;
    private Scanner scanner;

    public ATM() {
        accounts = new ArrayList<>();
        scanner = new Scanner(System.in);

    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void start() {
        System.out.println("Welcome to the ATM!");
        System.out.println("Welcome to the login page");
        System.out.print("Enter the account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter  the PIN: ");
        String pin = scanner.nextLine();

        Account account = validateAccount(accountNumber, pin);
        if (account != null) {
            System.out.println("\nLogin successful!");
            boolean isFinished = false;

            while (!isFinished) {
                System.out.println("\n1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Check Balance");
                System.out.println("4. Transfer");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        deposit(account);
                        break;
                    case 2:
                        withdraw(account);
                        break;
                    case 3:
                        checkBalance(account);
                        break;
                    case 4:
                        transfer(account);
                        break;
                    case 5:
                        isFinished = true;
                        break;
                    default:
                        System.out.println("\nInvalid choice!");
                }
            }
        } else {
            System.out.println("\nInvalid account number or PIN. Please try again.");
        }
    }

    private Account validateAccount(int accountNumber, String pin) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber && account.getPin().equals(pin)) {
                return account;
            }
        }
        return null;
    }

    private void deposit(Account account) {
        System.out.print("\nEnter the amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Deposit successful! New balance: " + account.getBalance());
    }

    private void withdraw(Account account) {
        System.out.print("\nEnter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (account.getBalance() >= amount) {
            account.withdraw(amount);
            System.out.println("Withdrawal successful! New balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    private void checkBalance(Account account) {
        System.out.println("Your current balance is: " + account.getBalance());
    }

    private void transfer(Account senderAccount) {
        System.out.print("\nEnter the recipient's account number: ");
        int recipientAccountNumber = scanner.nextInt();

        Account recipientAccount = findAccountByAccountNumber(recipientAccountNumber);

        if (recipientAccount != null) {
            System.out.print("Enter the amount to transfer: ");
            double amount = scanner.nextDouble();

            if (senderAccount.getBalance() >= amount) {
                senderAccount.withdraw(amount);
                recipientAccount.deposit(amount);

                System.out.println("Transfer successful! Transferred " + amount + " to account number " + recipientAccountNumber);
                System.out.println("Sender's new balance: " + senderAccount.getBalance());
            } else {
                System.out.println("Insufficient balance!");
            }
        } else {
            System.out.println("Recipient not found!");
        }
    }

    private Account findAccountByAccountNumber(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

}

public class AtmProject {
    public static void main(String[] args) {
        ATM atm = new ATM();

        // Add sample accounts
        Account account1 = new Account();
        Account account2 = new Account();
        atm.addAccount(account1);
        atm.addAccount(account2);

        atm.start();
    }
}








