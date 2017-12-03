package com.netcracker.metsko.entity;

import javax.persistence.*;
import java.util.*;
import java.time.LocalDate;

@Entity(name = "Inv_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column (nullable = false)
    private String description;

    @Column (nullable = false)
    private LocalDate dataOfOrder;

    @Column (nullable = false)
    private LocalDate dataOfComplete;

    @Column(nullable = false)
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

    @Column
    private boolean status;

    public Order() {
    }

    public Order(String name, String description, LocalDate dataOfOrder,
                 LocalDate dataOfComplete, String customerEmail, List<OrderItem> orderItemList,
                 double totalPrice, int itemAmount, boolean signPayment,
                 Date paymentDate, boolean status) {
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
        this.status = status;
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

    public LocalDate getDataOfOrder() {
        return dataOfOrder;
    }

    public void setDataOfOrder(LocalDate dataOfOrder) {
        this.dataOfOrder = dataOfOrder;
    }

    public LocalDate getDataOfComplete() {
        return dataOfComplete;
    }

    public void setDataOfComplete(LocalDate dataOfComplete) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItemList.add(orderItem);
        orderItem.setOrder(this);
    }

    public void removeOrderItem(OrderItem orderItem){
        this.orderItemList.remove(orderItem);
    }

    public void setGeneratedName()
    {
        this.name = customerEmail+'#'+id;
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
                isStatus() == order.isStatus() &&
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
        return Objects.hash(getId(), getName(), getDescription(), getDataOfOrder(), getDataOfComplete(), getCustomerEmail(), getOrderItemList(), getTotalPrice(), getItemAmount(), isSignPayment(), getPaymentDate(), isStatus());
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
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
