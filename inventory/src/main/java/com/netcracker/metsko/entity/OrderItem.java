package com.netcracker.metsko.entity;

import java.util.Date;
import java.util.Objects;

public class OrderItem {

    private  long id;

    private String name;

    private String description;

    private Date dateOfAddition;

    private String category;

    private double price;

    public OrderItem() {
    }

    public OrderItem(long id, String name, String description,
                     Date dateOfAddition, String category, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateOfAddition = dateOfAddition;
        this.category = category;
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem orderItem = (OrderItem) o;
        return getId() == orderItem.getId() &&
                Double.compare(orderItem.getPrice(), getPrice()) == 0 &&
                Objects.equals(getName(), orderItem.getName()) &&
                Objects.equals(getDescription(), orderItem.getDescription()) &&
                Objects.equals(getDateOfAddition(), orderItem.getDateOfAddition()) &&
                Objects.equals(getCategory(), orderItem.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getDateOfAddition(), getCategory(), getPrice());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderItem{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", dateOfAddition=").append(dateOfAddition);
        sb.append(", category='").append(category).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
