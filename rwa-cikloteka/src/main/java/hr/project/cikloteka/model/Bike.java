package hr.project.cikloteka.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bike")
@NamedQueries({
        @NamedQuery(name = "Bike.findAll", query = "SELECT b FROM Bike b"),
        @NamedQuery(name = "Bike.count", query = "SELECT COUNT(b) FROM Bike b"),
        @NamedQuery(name = "Bike.countByCategory", query = "SELECT COUNT(b) FROM Bike b JOIN b.category c WHERE c.id = :categoryId"),
        @NamedQuery(name = "Bike.findAllByCategory", query = "SELECT b FROM Bike b JOIN FETCH b.category c WHERE c.id = :categoryId"),
        @NamedQuery(name = "Bike.findById", query = "SELECT b FROM Bike b WHERE b.id = :id"),
        @NamedQuery(name = "Bike.findByYear", query = "SELECT b FROM Bike b WHERE b.modelYear = :modelYear"),
        @NamedQuery(name = "Bike.findByPrice", query = "SELECT b FROM Bike b WHERE b.price = :price"),
        @NamedQuery(name = "Bike.findByBrand", query = "SELECT b FROM Bike b WHERE b.brand = :brand")
})
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bike_name")
    private String name;

    @Column(name = "model_year")
    private Integer modelYear;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "bike")
    private List<OrderItem> orderItems = new ArrayList<>();

    public Bike() {
    }

    public Bike(Integer id) {
        this.id = id;
    }

    public Bike(Bike data) {
        copyData(data);
    }

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

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return name;
    }

    public void copyData(Bike data) {
        this.brand = data.getBrand();
        this.category = data.getCategory();
        this.name = data.getName();
        this.orderItems = data.getOrderItems();
        this.modelYear = data.getModelYear();
        this.price = data.getPrice();
        this.description = data.getDescription();
    }
}
