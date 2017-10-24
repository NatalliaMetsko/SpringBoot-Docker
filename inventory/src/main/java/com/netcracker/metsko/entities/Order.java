package com.netcracker.metsko.entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

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

    @ManyToMany
    public Offer orderItem;

    public Map<Long, Offer> orderList = new LinkedHashMap<Long, Offer>();

    void addItem(Offer offer)
    {
        orderList.put(id, offer);
    }

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
        return " Order#"+ id+" \n"+toString();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
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

    public void setTotalPrice(Map<Long, Offer> orderList) {

        this.totalPrice =  orderList.values().stream().mapToDouble(Offer::getPrice).sum();

    }

    public int getOfferAmount() {
        return offerAmount;
    }

    public void setOfferAmount(Map<Long, Offer> orderList) {
        this.offerAmount = orderList.size();
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

    public Order(String name, String description, Date dataOfOrder, Date dataOfComplete, String customerEmail, boolean signPayment, Date paymentDate) {
        this.name = name;
        this.description = description;
        this.dataOfOrder = dataOfOrder;
        this.dataOfComplete = dataOfComplete;
        this.customerEmail = customerEmail;
        this.signPayment = signPayment;
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

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", dataOfOrder=" + dataOfOrder +
                ", dataOfComplete=" + dataOfComplete +
                ", customerEmail='" + customerEmail + '\'' +
                ", orderList=" + orderList.values().toString() +
                ", totalPrice=" + totalPrice +
                ", offerAmount=" + offerAmount +
                ", signPayment=" + signPayment +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
