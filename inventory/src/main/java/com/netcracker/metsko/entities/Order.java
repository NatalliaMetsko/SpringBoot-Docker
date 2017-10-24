package com.netcracker.metsko.entities;

import javax.persistence.*;
import java.util.Date;

public class Order {

    @Id
    @GeneratedValue
    private long id;

    public String name;

    public String description;

    public Date dataOfOrder;

    public Date dataOfComplete;

    @OneToMany
    public String customerEmail;

//    @ManyToMany
//    Offer orderItem;

    public Double totalPrice;

    public int offerAmount;

    public boolean signPayment;

    public Date paymentDate;

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

    public Date getDataOfOrder() {
        return dataOfOrder;
    }

    public void setDataOfOrder(Date dataOfOrder) {
        this.dataOfOrder = dataOfOrder;
    }

    public Date getDataOfComplete() {
        return dataOfComplete;
    }

    public void setDataOfComplete(Date dataOfComplete) {
        this.dataOfComplete = dataOfComplete;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getOfferAmount() {
        return offerAmount;
    }

    public void setOfferAmount(int offerAmount) {
        this.offerAmount = offerAmount;
    }

    public boolean isSignPayment() {
        return signPayment;
    }

    public void setSignPayment(boolean signPayment) {
        this.signPayment = signPayment;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (getId() != order.getId()) return false;
        if (!getName().equals(order.getName())) return false;
        if (!getDescription().equals(order.getDescription())) return false;
        if (!getDataOfOrder().equals(order.getDataOfOrder())) return false;
        return getDataOfComplete().equals(order.getDataOfComplete());
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getName().hashCode();
        return result;
    }

    /*toString()*/
}
