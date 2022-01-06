package entities.miniprojet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "drink")
public class Drink {
    @Id
    @Column(name = "name", nullable = false, length = 20)
    private String id;

    @Column(name = "price")
    private Integer price;

    @Override
    public String toString() {
        return "Nom: " + id + " -- prix: " + price;
    }

    // accesseurs
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}