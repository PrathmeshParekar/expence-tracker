package in.sp.main.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Expence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Enter Expence Name")
    private String expencename;

    @NotBlank(message = "Enter ammount")
    private String ammount;

    @NotBlank(message = "Select Date")
    private String date;

    @NotBlank(message = "Enter Description")
    private String description;

    @ManyToOne
    private User user;

    public Expence() {
    }

    public Expence(String expencename, String ammount, String date, String description, User user) {
        this.expencename = expencename;
        this.ammount = ammount;
        this.date = date;
        this.description = description;
        this.user = user;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpencename() {
        return expencename;
    }

    public void setExpencename(String expencename) {
        this.expencename = expencename;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}