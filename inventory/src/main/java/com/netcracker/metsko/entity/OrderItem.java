package com.netcracker.metsko.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private Date dateOfAddition;

    @Column(nullable = false)
    private String category;

    @Column
    private double price;

    @Column
    private Long orderId;

    public OrderItem() {
    }

    public OrderItem(String name, String description, Date dateOfAddition,
                     String category, double price, Long order) {
        this.name = name;
        this.description = description;
        this.dateOfAddition = dateOfAddition;
        this.category = category;
        this.price = price;
        this.orderId = order;
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

    public Date getDateOfAddition() {
        return dateOfAddition;
    }

    public void setDateOfAddition(Date dateOfAddition) {
        this.dateOfAddition = dateOfAddition;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getOrder() {
        return orderId;
    }

    public void setOrder(Long order) {
        this.orderId = order;
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
                Objects.equals(getDateOfAddition(), orderItem.getDateOfAddition()) &&
                Objects.equals(getCategory(), orderItem.getCategory()) &&
                Objects.equals(getOrder(), orderItem.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getDateOfAddition(), getCategory(), getPrice(), getOrder());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderItemDao{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", dateOfAddition=").append(dateOfAddition);
        sb.append(", category='").append(category).append('\'');
        sb.append(", price=").append(price);
        sb.append(", order=").append(orderId);
        sb.append('}');
        return sb.toString();
    }
}
