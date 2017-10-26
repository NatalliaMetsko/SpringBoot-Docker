package com.netcracker.metsko.entities;

import java.util.*;

public class Order {


    private long id;

    private String name;

    private String description;

    private Date dataOfOrder;

    private Date dataOfComplete;

    private String customerEmail;

    public OrderItem orderItem;

    private Map<Long, OrderItem> orderList = new LinkedHashMap<Long, OrderItem>();

    public void addItem(OrderItem orderItem)
    {
        orderList.put(id, orderItem);
    }

    private Double totalPrice;

    private int itemAmount;

    private boolean signPayment;

    private Date paymentDate;

    private long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    private Date getDataOfOrder() {
        return dataOfOrder;
    }

    public void setDataOfOrder(Date dataOfOrder) {
        this.dataOfOrder = dataOfOrder;
    }

    private Date getDataOfComplete() {
        return dataOfComplete;
    }

    public void setDataOfComplete(Date dataOfComplete) {
        this.dataOfComplete = dataOfComplete;
    }

    private Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Map<Long, OrderItem> orderList) {

        this.totalPrice =  orderList.values().stream().mapToDouble(OrderItem::getPrice).sum();

    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Map<Long, OrderItem> orderList) {
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dataOfOrder=" + dataOfOrder +
                ", dataOfComplete=" + dataOfComplete +
                ", customerEmail='" + customerEmail + '\'' +
                ", orderItem=" + orderItem +
                ", orderList=" + orderList +
                ", totalPrice=" + totalPrice +
                ", itemAmount=" + itemAmount +
                ", signPayment=" + signPayment +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
