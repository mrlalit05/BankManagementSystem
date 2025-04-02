package Projects.BankManagementSystem;

// BankAccount.java (Parent Class)
public class BankAccount implements Transaction {

    private final String accountNumber;
    private final String accountHolder;
    private double balance;
    private final String password;
    private  String[] transactionHistory;
    private int transactionCount;


    @Override
    public void execute(double amount,String password) {
        if (amount > 0) {
            deposit(amount);
        } else {
            withdraw(-amount,password); // Negative amount means withdrawal
        }
    }

    @Override
    public void execute(){
        // Default transaction: could be balance check or other operation
        System.out.println("Current Balance: " + balance);
        addTransaction("Balance checked");
    }

    // Constructor
    public BankAccount(String accountNumber, String accountHolder, double balance,String password) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.password = password;
        this.transactionHistory = new String[100]; // Stores last 100 transactions
        this.transactionCount = 0;
    }
//    private void addTransaction(String description) {
//        if (transactionCount < 100) {
//            transactionHistory[transactionCount++] = description;
//        } else {
//            // Shift array to remove oldest transaction
//            System.arraycopy(transactionHistory, 1, transactionHistory, 0, 99);
//            transactionHistory[99] = description;
//        }
//    }
private void addTransaction(String description) {
    if (transactionHistory == null) {
        transactionHistory = new String[100]; // Ensure array is initialized
    }

    if (transactionCount < transactionHistory.length) {
        transactionHistory[transactionCount++] = description;
    } else {
        // Shift array left to remove the oldest transaction
        System.arraycopy(transactionHistory, 1, transactionHistory, 0, transactionHistory.length - 1);
        transactionHistory[transactionHistory.length - 1] = description;
    }
}

    //    public void printTransactionHistory() {
//        System.out.println("\n--- Transaction History ---");
//        for (int i = 0; i < transactionCount; i++) {
//            System.out.println((i+1) + ". " + transactionHistory[i]);
//        }
//    }
// Print Transaction History
public void printTransactionHistory() {
    if (transactionCount == 0) {
        System.out.println("No transactions yet.");
        return;
    }
    System.out.println("\n--- Transaction History ---");
    for (int i = 0; i < transactionCount; i++) {
        System.out.println((i + 1) + ". " + transactionHistory[i]);
    }
}
    // Getters & Setters (Encapsulation)
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }

    // Methods
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            addTransaction("Deposited: +" + amount);
            System.out.println("Deposited $" + amount + ". New Balance: $" + this.balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }
//    public void deposit(double amount) {
//        if (amount > 0) balance += amount;
//        this.balance += amount; // Using 'this' to refer to instance variable
//        System.out.println("Deposited $" + amount + ". New Balance: $" + this.balance);
//    }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }
    // Withdraw Method
    public void withdraw(double amount, String password) {
        if (!authenticate(password)) {
            System.out.println("Authentication failed!");
            return;
        }

        if (amount > 0 && amount <= balance) {
            this.balance -= amount;  // ✅ Deduct the amount from balance
            addTransaction("Withdrawal: -" + amount);
            System.out.println("Withdrawn $" + amount + ". Remaining Balance: $" + this.balance);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
//    public void withdraw(double amount,String password) {
//        if (!authenticate(password)) {
//            System.out.println("Authentication failed!");
//            return;
//        }
//
//        addTransaction("Withdrawal: -" + amount);
//    }
//        if (amount > 0 && amount <= balance) balance -= amount;
    }

// SavingsAccount.java (Child Class)
static class SavingsAccount extends BankAccount {
    private final double interestRate;

    public SavingsAccount(String accNum, String holder, double balance, double rate,String password) {
        super(accNum, holder, balance,password); // Calls parent constructor
        this.interestRate = rate;
    }

    public void applyInterest() {
        double interest = getBalance() * interestRate / 100;
        deposit(interest); // Uses parent's deposit()
    }
}
// CurrentAccount.java (Child Class)
static class CurrentAccount extends BankAccount {
    private final double overdraftLimit;

    public CurrentAccount(String accNum, String holder, double balance, double limit, String password) {
        super(accNum, holder, balance, password);
        this.overdraftLimit = limit;
    }

    @Override
    public void withdraw(double amount, String password) {
        if (amount > 0 && amount <= (getBalance() + overdraftLimit)) {
            super.withdraw(amount, password); // Uses parent's withdraw()
        }

    }
}
}
