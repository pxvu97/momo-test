import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomerAccount {
    private BigDecimal balance;
    private List<Bill> bills;

    private List<String> paymentTransactions;

    public CustomerAccount() {
        this.balance = BigDecimal.ZERO;
        this.bills = new ArrayList<>();
        this.paymentTransactions = new ArrayList<>();
    }

    public void viewBills() {
        System.out.println("Bill No. Type Amount Due Date State PROVIDER");
        for (Bill bill : bills) {
            System.out.println(MessageFormat.format("{0}. {1} {2} {3} {4} {5}"
                    , bill.getId(), bill.getType(), bill.getAmount()
                    , bill.getDueDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    , bill.getState(), bill.getProvider()));
        }
    }

    public Bill getBillById(int billId) {
        for (Bill bill : bills) {
            if (bill.getId() == billId) {
                return bill;
            }
        }
        System.out.println("Sorry! Not found a bill with such id " + billId);
        return null;
    }

    public void payBills(List<Integer> billIds) {
        List<Bill> validBills = new ArrayList<>();
        for (int billId : billIds) {
            validBills.add(getBillById(billId));
        }
        validBills = validBills.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Bill::getDueDate))
                .collect(Collectors.toList());

        for (Bill bill : validBills) {
            if (balance.compareTo(bill.getAmount()) >= 0) {
                bill.setState("PAID");
                balance = balance.subtract(bill.getAmount());
                paymentTransactions.add(bill.getAmount() + " " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " PROCESSED " + bill.getId());
                System.out.println(MessageFormat.format("Payment has been completed for Bill with id {0}.\n" +
                        "Your current balance is: {1}", bill.getId(), balance));
            } else {
                System.out.println("Sorry! Not enough fund to proceed with payment for id " + bill.getId());
            }
        }
    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }

    public void addFund(BigDecimal fund) {
        balance = balance.add(fund);
        System.out.println("Your available balance: " + balance);
    }

    public void schedulePayment(int billId, LocalDate paymentDate) {
        Bill bill = getBillById(billId);
        if (bill != null) {
            bill.setState("PENDING");
            paymentTransactions.add(bill.getAmount() + " " + paymentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " PENDING " + bill.getId());
            System.out.println("Payment for bill id " + billId + " is scheduled on " +
                    paymentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
    }

    public void viewPaymentTransactions() {
        System.out.println("No. Amount Payment Date State Bill Id");
        for (String transaction : paymentTransactions) {
            System.out.println(transaction);
        }
    }

    public void searchBillsByProvider(String provider) {
        System.out.println("Bill No. Type Amount Due Date State PROVIDER");
        for (Bill bill : bills) {
            if (bill.getProvider().equalsIgnoreCase(provider)) {
                System.out.println(MessageFormat.format("{0}. {1} {2} {3} {4} {5}"
                        , bill.getId(), bill.getType(), bill.getAmount()
                        , bill.getDueDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        , bill.getState(), bill.getProvider()));
            }
        }
    }
}
