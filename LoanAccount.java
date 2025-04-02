package Projects.BankManagementSystem;

public class LoanAccount extends BankAccount {
    private final double loanAmount;
    private final double interestRate;
    private final int loanTerm; // in months

    public LoanAccount(String accNum, String holder, double balance,
                       double loanAmount, double rate, int term,String password) {
        super(accNum, holder, balance,password);
        this.loanAmount = loanAmount;
        this.interestRate = rate;
        this.loanTerm = term;
    }

    public double calculateEMI() {
        double monthlyRate = interestRate / 12 / 100;
        return (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, loanTerm))
                / (Math.pow(1 + monthlyRate, loanTerm) - 1);
    }

}
