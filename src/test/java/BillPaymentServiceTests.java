import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
public class BillPaymentServiceTests {
    private static CustomerAccount customerAccount = new CustomerAccount();

    @Test
    public void testViewBills() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        customerAccount.viewBills();
        String expectedOutput = "Bill\tNo.\tType\tAmount\tDue Date\tState\tPROVIDER\n" +
                "1.\tELECTRIC\t200000\t25/10/2020\tNOT_PAID\tEVN HCMC\n" +
                "2.\tWATER\t175000\t30/10/2020\tNOT_PAID\tSAVACO HCMC\n" +
                "3.\tINTERNET\t800000\t30/11/2020\tNOT_PAID\tVNPT\n";
        Assert.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPayBills() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

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
        String expectedOutput = "Sorry! Not found a bill with such id";
        Assert.assertEquals(expectedOutput, outputStream.toString());
    }
    @Test
    public void testPayBillsNotEnoughBalance() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        customerAccount.payBills(Arrays.asList(2, 3));
        String expectedOutput = "Sorry! Not enough fund to proceed with payment.";
        Assert.assertEquals(expectedOutput, outputStream.toString());
    }
}
