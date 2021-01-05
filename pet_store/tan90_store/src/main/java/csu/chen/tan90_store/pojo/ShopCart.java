package csu.chen.tan90_store.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import csu.chen.tan90_store.pojo.goods.Item;
import csu.chen.tan90_store.pojo.user.Account;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shop_cart")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class ShopCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    int id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    Item item;


    String username;

    int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
