package Projects.BankManagementSystem;

public interface Transaction {
    void execute(double amount,String password);

    // Executes a default transaction (for operations needing no amount)


    void execute();
}
