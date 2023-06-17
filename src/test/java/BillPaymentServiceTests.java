import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

public class BillPaymentServiceTests {
    private static CustomerAccount customerAccount = new CustomerAccount();

    @Before
    public void setupCustomerAccount() {
        Bill bill1 = new Bill(1, "ELECTRIC", BigDecimal.valueOf(200000), LocalDate.of(2020, 10, 25), "NOT_PAID", "EVN HCMC");
        Bill bill2 = new Bill(2, "WATER", BigDecimal.valueOf(175000), LocalDate.of(2020, 10, 30), "NOT_PAID", "SAVACO HCMC");
        Bill bill3 = new Bill(3, "INTERNET", BigDecimal.valueOf(800000), LocalDate.of(2020, 11, 30), "NOT_PAID", "VNPT");
        Bill bill4 = new Bill(4, "RENT", BigDecimal.valueOf(2000000), LocalDate.of(2020, 11, 30), "NOT_PAID", "REE");

        customerAccount.addBill(bill1);
        customerAccount.addBill(bill2);
        customerAccount.addBill(bill3);
        customerAccount.addBill(bill4);
        customerAccount.addFund(BigDecimal.valueOf(1000000));
    }

    @Test
    public void addFund() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        customerAccount.addFund(BigDecimal.valueOf(1000000));
        String expectedOutput = "Your available balance: 2000000\r\n";
        Assert.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testViewBills() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        customerAccount.viewBills();
        String expectedOutput = "Bill No. Type Amount Due Date State PROVIDER\n" +
                "1. ELECTRIC 200000 25/10/2020 NOT_PAID EVN HCMC\n" +
                "2. WATER 175000 30/10/2020 NOT_PAID SAVACO HCMC\n" +
                "3. INTERNET 800000 30/11/2020 NOT_PAID VNPT\n" +
                "4. RENT 2000000 30/11/2020 NOT_PAID REE\n";
        Assert.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPayBills() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        customerAccount.addFund(BigDecimal.valueOf(1000000));
        customerAccount.payBills(Collections.singletonList(1));
        String expectedOutput = "Payment has been completed for Bill with id 1.\n" +
                "Your current balance is: 800000";
        Assert.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPayBillsNotFound() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        customerAccount.payBills(Collections.singletonList(10));
        String expectedOutput = "Sorry! Not found a bill with such id 10\r\n";
        Assert.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPayBillsNotEnoughBalance() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        customerAccount.payBills(Arrays.asList(4));
        String expectedOutput = "Sorry! Not enough fund to proceed with payment for id 4\r\n";
        Assert.assertEquals(expectedOutput, outputStream.toString());
    }

    @After
    public void clearData() {
        customerAccount = new CustomerAccount();
    }
}
