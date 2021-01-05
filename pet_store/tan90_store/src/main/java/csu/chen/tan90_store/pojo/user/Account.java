package csu.chen.tan90_store.pojo.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "account")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    int id;
    @OneToOne
    @JoinColumn(name = "uid")
    SimAccount simAccount;

    @ManyToOne
    @JoinColumn(name = "gid")
    UserGroup userGroup;
    String name;
    String telephone;
    String country;
    String state;
    String city;
    String detailLocate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SimAccount getSimAccount() {
        return simAccount;
    }

    public void setSimAccount(SimAccount simAccount) {
        this.simAccount = simAccount;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetailLocate() {
        return detailLocate;
    }

    public void setDetailLocate(String detailLocate) {
        this.detailLocate = detailLocate;
    }
}
