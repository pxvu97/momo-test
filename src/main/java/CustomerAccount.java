import java.util.ArrayList;
import java.util.List;

public class CustomerAccount {
    private double balance;
    private List<Bill> bills;

    private List<String> paymentTransactions;

    public CustomerAccount() {
        this.balance = 0;
        this.bills = new ArrayList<>();
        this.paymentTransactions = new ArrayList<>();
    }

    public void viewBills() {

    }

    public void payBills(List<Integer> billIds) {

    }
}
