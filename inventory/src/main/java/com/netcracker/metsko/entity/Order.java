package com.netcracker.metsko.entity;

import javax.persistence.*;
import java.util.*;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Date dataOfOrder;

    @Column
    private Date dataOfComplete;

    @Column
    private String customerEmail;

    @OneToMany
    private List<OrderItem> orderItemList;

    @Column
    private double totalPrice;

    @Column
    private int itemAmount;

    @Column
    private boolean signPayment;

    @Column
    private Date paymentDate;

    public Order() {
    }

    public Order(String name, String description, Date dataOfOrder, Date dataOfComplete,
                 String customerEmail, List<OrderItem> orderItemList, double totalPrice,
                 int itemAmount, boolean signPayment, Date paymentDate) {
        this.name = name;
        this.description = description;
        this.dataOfOrder = dataOfOrder;
        this.dataOfComplete = dataOfComplete;
        this.customerEmail = customerEmail;
        this.orderItemList = orderItemList;
        this.totalPrice = totalPrice;
        this.itemAmount = itemAmount;
        this.signPayment = signPayment;
        this.paymentDate = paymentDate;
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

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
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

    public void addOrderItem(OrderItem orderItem){
        this.orderItemList.add(orderItem);
        orderItem.setOrder(this);
    }

    public void removeOrderItem(OrderItem orderItem){
        this.orderItemList.remove(orderItem);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Order)) return false;
        Order order = (Order) object;
        return getId() == order.getId() &&
                Double.compare(order.getTotalPrice(), getTotalPrice()) == 0 &&
                getItemAmount() == order.getItemAmount() &&
                isSignPayment() == order.isSignPayment() &&
                Objects.equals(getName(), order.getName()) &&
                Objects.equals(getDescription(), order.getDescription()) &&
                Objects.equals(getDataOfOrder(), order.getDataOfOrder()) &&
                Objects.equals(getDataOfComplete(), order.getDataOfComplete()) &&
                Objects.equals(getCustomerEmail(), order.getCustomerEmail()) &&
                Objects.equals(getOrderItemList(), order.getOrderItemList()) &&
                Objects.equals(getPaymentDate(), order.getPaymentDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getDataOfOrder(), getDataOfComplete(), getCustomerEmail(), getOrderItemList(), getTotalPrice(), getItemAmount(), isSignPayment(), getPaymentDate());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", dataOfOrder=").append(dataOfOrder);
        sb.append(", dataOfComplete=").append(dataOfComplete);
        sb.append(", customerEmail='").append(customerEmail).append('\'');
        sb.append(", orderItemList=").append(orderItemList);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", itemAmount=").append(itemAmount);
        sb.append(", signPayment=").append(signPayment);
        sb.append(", paymentDate=").append(paymentDate);
        sb.append('}');
        return sb.toString();
    }
}
