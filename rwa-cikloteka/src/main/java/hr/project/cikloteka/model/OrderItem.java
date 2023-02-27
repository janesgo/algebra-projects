package hr.project.cikloteka.model;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@NamedQueries({
        @NamedQuery(name = "OrderItem.findAll", query = "SELECT o FROM OrderItem o"),
})
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "bike_id")
    private Bike bike;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private OrderDetails order;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;

    public OrderItem() {
    }

    public OrderItem(Bike bike, Integer quantity, Double price) {
        this.bike = bike;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem(Integer id) {
        this.id = id;
    }

    public OrderItem(OrderItem data) {
        copyData(data);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public OrderDetails getOrder() {
        return order;
    }

    public void setOrder(OrderDetails order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void copyData(OrderItem data) {
        bike = data.getBike();
        order = data.getOrder();
        quantity = data.getQuantity();
        price = data.getPrice();
    }
}
