package hr.project.cikloteka.model;

import javax.persistence.*;

@Entity
@Table(name = "login")
@NamedQueries({
        @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l")
})
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user")
    private String user;

    @Column(name = "time")
    private String time;

    @Column(name = "address")
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Login() {
    }
    public Login(Login data) {
        copyData(data);
    }

    public void copyData(Login data) {
        this.user = data.getUser();
        this.time = data.getTime();
        this.address = data.getAddress();
    }
}
