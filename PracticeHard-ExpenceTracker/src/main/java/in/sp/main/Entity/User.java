package in.sp.main.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "please Enter username")
    private String username;

    @NotBlank(message = "plase fill email address")
    @Email(message = "Invalid eamil")
    private String email;

    @NotBlank(message = "password requried")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
            message = "pasword must be one uppercasse,one lowercases,one specail caharcter and at least 8 charck"

    )
    private String password;

    @NotBlank(message = "confirm password requried")
    @Transient
    private String confirm;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Expence> expence = new ArrayList<>();


    public User() {
    }

    public User(String username, String email, String password, String confirm, List<Expence> expence) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
        this.expence = expence;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public List<Expence> getExpence() {
        return expence;
    }

    public void setExpence(List<Expence> expence) {
        this.expence = expence;
    }
}
