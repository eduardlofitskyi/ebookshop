package Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduard on 12.11.2014.
 */
public class Cart {
    private ArrayList<Book> list;

    public Cart() {
        list = new ArrayList<>();
    }

    public void add(int id, String author,  String title, float price, int amount){
        for (Book book:list){
            if (book.getId()==id){
                book.setAmountOrdered(book.getAmountOrdered()+amount);
                return;
            }
        }
        list.add(new Book(id,author,title,price,amount));
    }

    public boolean update(int id, int amount){
        for (Book book:list){
            if (book.getId()==id){
                book.setAmountOrdered(amount);
                return true;
            }
        }
        return false;
    }

    public void remove(int id){
        for (Book book:list){
            if (book.getId()==id){
                list.remove(book);
                return;
            }
        }
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public List<Book> getItems(){
        return list;
    }

    public void clear(){
        list.clear();
    }

}
