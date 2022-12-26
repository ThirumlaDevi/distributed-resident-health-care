package ucd.healthcare.tdpharmacy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Table to store order details.
 */
@Entity
@Table(name = "pharma1order")
public class TDPharmaOrder implements Serializable{

    public TDPharmaOrder() {}

    public TDPharmaOrder(String order) {this.order = order;}
    @Id
    // @Column(name = "id", nullable = false, columnDefinition = "integer default 0")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "order")
    private String order;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TDPharmaOrder [id=" + id + ", order=" + order + "]";
    }
}
