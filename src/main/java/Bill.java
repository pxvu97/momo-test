import java.math.BigDecimal;
import java.time.LocalDate;

public class Bill {
    private int id;
    private String type;
    private BigDecimal amount;
    private LocalDate dueDate;
    private String state;
    private String provider;

    public Bill(int id, String type, BigDecimal amount, LocalDate dueDate, String state, String provider) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.dueDate = dueDate;
        this.state = state;
        this.provider = provider;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProvider() {
        return provider;
    }
}
