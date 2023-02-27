package hr.project.cikloteka.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payment")
@NamedQueries({
        @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p"),
        @NamedQuery(name = "Payment.findById", query = "SELECT p FROM Payment p WHERE p.id = :id")
})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "payment_name")
    private String name;

    @OneToMany(mappedBy = "payment")
    private List<OrderDetails> orders = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrderDetails> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDetails> orders) {
        this.orders = orders;
    }
}
