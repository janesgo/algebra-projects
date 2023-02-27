package hr.project.cikloteka.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
        @NamedQuery(name = "Customer.findByEmailAndPassword", query = "SELECT c FROM Customer c WHERE c.email = :email and c.password = :password")
})
public class Customer {

    public static final String ADMIN = "admin";
    public static final String USER = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    @NotNull
    @Size(max = 255)
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @Size(max = 255)
    private String lastName;

    @Column(name = "email")
    @NotNull
    @Size(max = 255)
    private String email;

    @Column(name = "password")
    @NotNull
    @Size(max = 255)
    private String password;

    @Column(name = "user_type")
    @NotNull
    @Size(max = 100)
    private String userType;

    @Column(name = "address")
    @Size(max = 255)
    private String address;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "customer")
    private List<OrderDetails> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(Integer id) {
        this.id = id;
    }

    public Customer(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        userType = USER;
    }

    public Customer(Customer data) {
        copyData(data);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<OrderDetails> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDetails> orders) {
        this.orders = orders;
    }

    public void copyData(Customer data) {
        firstName = data.getFirstName();
        lastName = data.getLastName();
        email = data.getEmail();
        password = data.getPassword();
        userType = data.getUserType();
        address = data.getAddress();
        city = data.getCity();
        orders = data.getOrders();
    }
}
