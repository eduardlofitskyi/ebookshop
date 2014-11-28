package Entity;

/**
 * Created by Eduard on 09.11.2014.
 */
public class User {

    private int id;
    private String name;
    private String email;
    private String pass;
    private String phone;

    public User(String name, String email, String pass, String phone) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.phone = phone;
    }
    public User() {
    }

    public String getPhone() {
        return phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id_user) {
        this.id = id_user;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
