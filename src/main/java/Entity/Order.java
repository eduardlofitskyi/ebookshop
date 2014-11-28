package Entity;

/**
 * Created by Eduard on 20.11.2014.
 */
public class Order {

    private String date;
    private String author;
    private int amount;

    public float getTotalPrice() {
        return totalPrice;
    }

    private float totalPrice;

    private String title;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Order(String date, String author, String title, int amount, float totalPrice) {
        this.date = date;
        this.author = author;
        this.title = title;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
