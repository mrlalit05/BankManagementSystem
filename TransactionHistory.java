package Projects.BankManagementSystem;

public class TransactionHistory {
    private String[] transactions;  // Array to store transaction records
    private int count;             // Current number of transactions
    private int capacity;          // Maximum transactions to store

    // Constructor
    public TransactionHistory(int capacity) {
        this.capacity = capacity;
        this.transactions = new String[capacity];
        this.count = 0;
    }

    // Add a new transaction
    public void addTransaction(String description) {
        if (count < capacity) {
            transactions[count] = description;  // Add to next available slot
            count++;
        } else {
            // Shift all transactions left (remove oldest)
            for (int i = 0; i < capacity - 1; i++) {
                transactions[i] = transactions[i + 1];
            }
            transactions[capacity - 1] = description;  // Add new at end
        }
    }

    // Print all transactions
    public void printHistory() {
        System.out.println("\n--- Transaction History ---");
        if (count == 0) {
            System.out.println("No transactions yet.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + transactions[i]);
        }
    }
}