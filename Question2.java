import java.util.Scanner;

abstract class BankAccount {
    protected String accountNumber;
    protected double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: ₹" + amount + ", New Balance: ₹" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public abstract void withdraw(double amount);
}

interface Transaction {
    void transfer(BankAccount toAccount, double amount);
}

class SavingsAccount extends BankAccount implements Transaction {
    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    // override
    public void withdraw(double amount) {
        if (balance - amount >= 500) {
            balance -= amount;
            System.out.println("Withdrawn: ₹" + amount + ", New Balance: ₹" + balance);
        } else {
            System.out.println("Withdrawal failed! Minimum balance of ₹500 required.");
        }
    }

    // override
    public void transfer(BankAccount toAccount, double amount) {
        if (balance - amount >= 500) {
            balance -= amount;
            toAccount.deposit(amount);
            System.out.println("Transferred: ₹" + amount + " to " + toAccount.accountNumber);
        } else {
            System.out.println("Transfer failed! Minimum balance of ₹500 required.");
        }
    }
}

class CurrentAccount extends BankAccount implements Transaction {
    private static final double OVERDRAFT_LIMIT = 5000;

    public CurrentAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    // override
    public void withdraw(double amount) {
        if (balance - amount >= -OVERDRAFT_LIMIT) {
            balance -= amount;
            System.out.println("Withdrawn: ₹" + amount + ", New Balance: ₹" + balance);
        } else {
            System.out.println("Withdrawal failed! Overdraft limit exceeded.");
        }
    }

    // override
    public void transfer(BankAccount toAccount, double amount) {
        if (balance - amount >= -OVERDRAFT_LIMIT) {
            balance -= amount;
            toAccount.deposit(amount);
            System.out.println("Transferred: ₹" + amount + " to " + toAccount.accountNumber);
        } else {
            System.out.println("Transfer failed! Overdraft limit exceeded.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Hey Mam, Please enter the initial balance for the Savings Account: ");
        double savingsBalance = getValidAmount(scanner);
        BankAccount savings = new SavingsAccount("SAV123", savingsBalance);

        System.out.println("Hey Mam, Please enter the initial balance for the Current Account: ");
        double currentBalance = getValidAmount(scanner);
        BankAccount current = new CurrentAccount("CUR456", currentBalance);

        savings.deposit(1000);
        current.withdraw(3000);

        ((Transaction) savings).transfer(current, 1500); // Explicit cast to Transaction
        ((Transaction) current).transfer(savings, 6000); // Explicit cast to Transaction
    }

    private static double getValidAmount(Scanner scanner) {
        double amount;
        while (true) {
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount >= 0) {
                    return amount;
                } else {
                    System.out.println("Oops! Please enter a valid non-negative amount: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Oops! That doesn't seem like a valid number. Please try again: ");
            }
        }
    }
}
