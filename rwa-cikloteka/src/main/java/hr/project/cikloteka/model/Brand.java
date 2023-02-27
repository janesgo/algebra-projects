package hr.project.cikloteka.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brand")
@NamedQueries({
        @NamedQuery(name = "Brand.findAll", query = "SELECT b FROM Brand b")})
@NamedEntityGraph(
        name = "graph.Brand.bikes",
        attributeNodes = {@NamedAttributeNode("bikes")})
public class Brand {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "brand_name")
    private String name;

    @OneToMany(mappedBy = "brand")
    private List<Bike> bikes = new ArrayList<>();

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
}
