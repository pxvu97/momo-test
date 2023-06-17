import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BillPaymentService {
    private static Scanner scanner = new Scanner(System.in);
    private static CustomerAccount customerAccount = new CustomerAccount();

    public static void main(String[] args) {
        String command;
        do {
            System.out.print("Enter command: ");
            command = scanner.nextLine();
            processCommand(command);

        } while (!"EXIT".equals(command));
    }

    public static void processCommand(String command) {
        String[] params = command.split(" ");
        String operation = params[0];
        switch (operation) {
            case "CASH_IN":
                BigDecimal amount = new BigDecimal(params[1]);
                customerAccount.addFund(amount);
                break;
            case "LIST_BILL":
                customerAccount.viewBills();
                break;
            case "PAY":
                List<Integer> billIds = new ArrayList<>();
                for (int i = 1; i < params.length; i++) {
                    billIds.add(Integer.parseInt(params[i]));
                }
                customerAccount.payBills(billIds);
                break;
            case "SCHEDULE":
                int billId = Integer.parseInt(params[1]);
                LocalDate paymentDate = LocalDate.parse(params[2], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                customerAccount.schedulePayment(billId, paymentDate);
                break;
            case "LIST_PAYMENT":
                customerAccount.viewPaymentTransactions();
                break;
            case "SEARCH_BILL_BY_PROVIDER":
                String provider = params[1];
                customerAccount.searchBillsByProvider(provider);
                break;
            case "EXIT":
                break;
            default:
                System.out.println("Invalid command.");
        }
    }
}
