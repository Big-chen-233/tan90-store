package csu.chen.tan90_store.pojo.goods;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import csu.chen.tan90_store.pojo.user.Account;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    int id;

    @ManyToOne
    @JoinColumn(name = "pid")
    Product product;

    @ManyToOne
    @JoinColumn(name = "sid")
    Account account;

    String name;
    BigDecimal price;
    int quantity;
    String describe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
