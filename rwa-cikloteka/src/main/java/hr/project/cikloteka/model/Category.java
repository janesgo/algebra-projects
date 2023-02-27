package hr.project.cikloteka.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
        @NamedQuery(name = "Category.count", query = "SELECT COUNT(c) FROM Category c")
})
@NamedEntityGraph(
        name = "graph.Category.bikes",
        attributeNodes = {@NamedAttributeNode("bikes")})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Bike> bikes = new ArrayList<>();

    public Category() {
    }

    public Category(Integer id) {
        this.id = id;
    }

    public Category(Category data) {
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

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }

    @Override
    public String toString() {
        return name;
    }

    public void copyData(Category data) {
        name = data.getName();
        bikes = data.getBikes();
    }
}
