package com.netcracker.metsko.entity;

import java.util.*;


public class Order {


    private long id;

    private String name;

    private String description;

    private Date dataOfOrder;

    private Date dataOfComplete;

    private String customerEmail;

    private List<OrderItem> orderList;

    private double totalPrice;

    private int itemAmount;

    private boolean signPayment;

    private Date paymentDate;

    public Order() {
    }

    public Order(long id, String name, String description, Date dataOfOrder, Date dataOfComplete,
                 String customerEmail, List<OrderItem> orderList, double totalPrice,
                 int itemAmount, boolean signPayment, Date paymentDate) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.dataOfOrder = dataOfOrder;
        this.dataOfComplete = dataOfComplete;
        this.customerEmail = customerEmail;
        this.orderList = orderList;
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

    public List<OrderItem> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderItem> orderList) {
        this.orderList = orderList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = orderList.stream().mapToDouble(OrderItem::getPrice).sum();
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = orderList.size();
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

    public void addItem(OrderItem orderItem){
        orderList.add(orderItem);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId() == order.getId() &&
                Double.compare(order.getTotalPrice(), getTotalPrice()) == 0 &&
                getItemAmount() == order.getItemAmount() &&
                isSignPayment() == order.isSignPayment() &&
                Objects.equals(getName(), order.getName()) &&
                Objects.equals(getDescription(), order.getDescription()) &&
                Objects.equals(getDataOfOrder(), order.getDataOfOrder()) &&
                Objects.equals(getDataOfComplete(), order.getDataOfComplete()) &&
                Objects.equals(getCustomerEmail(), order.getCustomerEmail()) &&
                Objects.equals(getOrderList(), order.getOrderList()) &&
                Objects.equals(getPaymentDate(), order.getPaymentDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getDataOfOrder(), getDataOfComplete(), getCustomerEmail(), getOrderList(), getTotalPrice(), getItemAmount(), isSignPayment(), getPaymentDate());
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
        sb.append(", orderList=").append(orderList);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", itemAmount=").append(itemAmount);
        sb.append(", signPayment=").append(signPayment);
        sb.append(", paymentDate=").append(paymentDate);
        sb.append('}');
        return sb.toString();
    }
}
