package Projects.BankManagementSystem;

import java.util.Scanner;

// Bank.java (Main Class)
public class Bank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create accounts
        BankAccount.SavingsAccount savings = new BankAccount.SavingsAccount("SA001", "Alice", 5000, 5.0,"abc123");
        BankAccount.CurrentAccount current = new BankAccount.CurrentAccount("CA001", "Bob", 3000, 1000,"1234");
//        LoanAccount loan = new LoanAccount("LA001", "Charlie", 0, 10000, 8.5, 12);

        while (true) {
            System.out.println("\n1. Savings Account\n2. Current Account\n3. Loan Account\n4. Exit");
            System.out.print("Choose account type: ");
            int choice = scanner.nextInt();

            if (choice == 4) break;

            System.out.print("Enter password: ");
            String pwd = scanner.next();

            BankAccount account = null;
            switch (choice) {
                case 1:
                    account = savings;
                    break;
                case 2:
                    account = current;
                    break;
//                case 3:
//                    account = loan;
//                    break;
                default:
                    System.out.println("Please Enter valid input:");
                    break;
            }

            if (account == null || !account.authenticate(pwd)) {
                System.out.println("Invalid choice or password!");
                continue;
            }

            // Account operations menu
            System.out.println("\n1. Deposit\n2. Withdraw\n3. Check Balance\n4. Transaction History\n5. Back");
            System.out.print("Choose operation: ");
            int op = scanner.nextInt();

            switch (op) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmt = scanner.nextDouble();
                    account.deposit(depositAmt);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmt = scanner.nextDouble();
                    account.withdraw(withdrawAmt,"1234");
                    break;
                case 3:
                    System.out.println("Balance: " + account.getBalance());
                    break;
                case 4:
                    System.out.println("TransactionHistory");
                    account.printTransactionHistory();
                    break;
                case 5:
                    System.out.println("Returning to the main menu...");
                    break;
                default:
                    System.out.println("Please Enter the valid Input:");
                    break;

            }
            if (op == 5) break;// Exit inner loop to return to account selection
        }

    }
}
