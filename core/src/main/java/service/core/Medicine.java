package service.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

// @Entity
// @Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Medicine {
    // @Id
    // @Column(name = "id", nullable = false)
    // @GeneratedValue(strategy = GenerationType.TABLE)
    // // @GeneratedValue(strategy=GenerationType.IDENTITY)
    // public Integer id;

    // @Column(name = "name", nullable = false)
    // public String name;

    // @Column(name = "price", nullable = false)
    // public Double price;

    // @Column(name = "quantity", nullable = false)
    // public Integer quantity;

    // public Integer getId() {
    //     return id;
    // }

    // public void setId(Integer id) {
    //     this.id = id;
    // }

    // public String getName() {
    //     return name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }

    // public Double getPrice() {
    //     return price;
    // }

    // public void setPrice(Double price) {
    //     this.price = price;
    // }

    // public Integer getQuantity() {
    //     return quantity;
    // }

    // public void setQuantity(Integer quantity) {
    //     this.quantity = quantity;
    // }
}

