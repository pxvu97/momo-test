import java.time.LocalDate;

public class Bill {
    private int id;
    private String type;
    private double amount;
    private LocalDate dueDate;
    private String state;
    private String provider;

    public Bill(int id, String type, double amount, LocalDate dueDate, String state, String provider) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.dueDate = dueDate;
        this.state = state;
        this.provider = provider;
    }
}
