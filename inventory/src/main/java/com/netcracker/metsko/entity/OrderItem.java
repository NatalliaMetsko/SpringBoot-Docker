package com.netcracker.metsko.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @Column
    private String description;



    @Column
    private double price;

    @ManyToOne
    @JsonBackReference
    private Order order;

    public OrderItem() {
    }

    public OrderItem(String name, String description,
                     double price, Order order) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof OrderItem)) return false;
        OrderItem orderItem = (OrderItem) object;
        return getId() == orderItem.getId() &&
                Double.compare(orderItem.getPrice(), getPrice()) == 0 &&
                Objects.equals(getName(), orderItem.getName()) &&
                Objects.equals(getDescription(), orderItem.getDescription()) &&
                Objects.equals(getOrder(), orderItem.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(),  getPrice(), getOrder());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderItemDao{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", order=").append(order);
        sb.append('}');
        return sb.toString();
    }
}
