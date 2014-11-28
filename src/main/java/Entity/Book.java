package Entity;

/**
 * Created by Eduard on 12.11.2014.
 */
public class Book {

    private int id;
    private String title;
    private String author;
    private float price;
    private int amountOrdered;

    public Book(int id, String title, String author, float price, int amountOrdered) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.amountOrdered = amountOrdered;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public float getPrice() {
        return price;
    }

    public int getAmountOrdered() {
        return amountOrdered;
    }

    public void setAmountOrdered(int amountOrdered) {
        this.amountOrdered = amountOrdered;
    }
}
