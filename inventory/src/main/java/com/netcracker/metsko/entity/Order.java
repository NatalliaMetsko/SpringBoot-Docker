package com.netcracker.metsko.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.netcracker.metsko.entity.enums.Status;
import com.netcracker.metsko.util.LocalDateDeserializer;
import com.netcracker.metsko.util.LocalDateSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Entity(name = "InvOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataOfCreation;

    @Column
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataOfReceive;

    @Column
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataOfCompletion;

    @Column
    @NotNull
    private String customerEmail;

    @OneToMany
    private List<OrderItem> orderItemList;

    @Column
    private double totalPrice;

    @Column
    private int itemAmount;

    @Column
    private boolean signPayment = false;

    @Column
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate paymentDate;

    @Column
    private String status;

    public Order() {
    }

    public Order(String customerEmail, String status) {
        this.name = "Order#" + getNumber();
        this.dataOfCreation = LocalDate.now();
        this.customerEmail = customerEmail;
        this.dataOfReceive = null;
        this.dataOfCompletion = null;
        this.orderItemList = Collections.EMPTY_LIST;
        this.totalPrice = 0;
        this.itemAmount = 0;
        this.paymentDate = null;
        this.status = status;
    }

    public Order(String name, LocalDate dataOfOrder, LocalDate dataOfReceive, LocalDate dataOfCompletion,
                 String customerEmail, List<OrderItem> orderItemList, double totalPrice,
                 int itemAmount, LocalDate paymentDate, String status) {
        this.name = name;
        this.dataOfCreation = dataOfOrder;
        this.dataOfReceive = dataOfReceive;
        this.dataOfCompletion = dataOfCompletion;
        this.customerEmail = customerEmail;
        this.orderItemList = orderItemList;
        this.totalPrice = totalPrice;
        this.itemAmount = itemAmount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public LocalDate getDataOfCreation() {
        return dataOfCreation;
    }

    public void setDataOfCreation(LocalDate dataOfCreation) {
        this.dataOfCreation = dataOfCreation;
    }

    public LocalDate getDataOfReceive() {
        return dataOfReceive;
    }

    public void setDataOfReceive() {
        this.dataOfReceive = paymentDate.plusDays(7);
        this.status = String.valueOf(Status.ACTIVE);
    }

    public LocalDate getDataOfCompletion() {
        return dataOfCompletion;
    }

    public void setDataOfCompletion() {
        this.dataOfCompletion = dataOfReceive.plusYears(1);
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
        this.totalPrice = orderItemList.stream().mapToDouble(OrderItem::getPrice).sum();
        return totalPrice;
    }

    private void setTotalPrice() {
        this.totalPrice = orderItemList.stream().mapToDouble(OrderItem::getPrice).sum();
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getItemAmount() {
        this.itemAmount = orderItemList.size();
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    private void setItemAmount() {
        this.itemAmount = orderItemList.size();
    }

    public boolean getSignPayment() {
        return signPayment;
    }

    public void setSignPayment() {
        this.signPayment = true;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus() {
        if (this.orderItemList.size() == 0) {
            this.status = String.valueOf(Status.EMPTY);
        } else {
            if (!this.signPayment) {
                this.status = String.valueOf(Status.PENDING);
            } else {
                if ((this.signPayment) && (paymentDate.isBefore(dataOfReceive))) {
                    this.status = String.valueOf(Status.IN_PROGRESS);
                } else {
                    if (LocalDate.now().equals(dataOfCompletion)) {
                        this.signPayment = false;
                        this.status = String.valueOf(Status.TERMINATED);
                    }
                }
            }
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addOrderItem(OrderItem orderItem) {
        if (!this.status.equals(String.valueOf(Status.CANCELED))) {
            this.orderItemList.add(orderItem);
            orderItem.setOrder(this);
            this.setItemAmount();
            this.totalPrice = totalPrice + orderItem.getPrice();
            this.status = String.valueOf(Status.PENDING);
        } else {
            System.out.println(getName() + " canceled\n");
        }
    }

    public void removeOrderItem(OrderItem orderItem) {
        if (this.status.equals(String.valueOf(Status.PENDING))) {
            this.orderItemList.remove(orderItem);
        } else System.out.println(getName() + " canceled\n");

    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Order)) return false;
        Order order = (Order) object;
        return getId() == order.getId() &&
                Double.compare(order.getTotalPrice(), getTotalPrice()) == 0 &&
                getItemAmount() == order.getItemAmount() &&
                getSignPayment() == order.getSignPayment() &&
                Objects.equals(getName(), order.getName()) &&
                Objects.equals(getDataOfCreation(), order.getDataOfCreation()) &&
                Objects.equals(getDataOfReceive(), order.getDataOfReceive()) &&
                Objects.equals(getDataOfCompletion(), order.getDataOfCompletion()) &&
                Objects.equals(getCustomerEmail(), order.getCustomerEmail()) &&
                Objects.equals(getOrderItemList(), order.getOrderItemList()) &&
                Objects.equals(getPaymentDate(), order.getPaymentDate()) &&
                Objects.equals(getStatus(), order.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDataOfCreation(), getDataOfReceive(), getDataOfCompletion(), getCustomerEmail(), getOrderItemList(), getTotalPrice(), getItemAmount(), getSignPayment(), getPaymentDate(), getStatus());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", dataOfCreation=").append(dataOfCreation);
        sb.append(", dataOfReceive=").append(dataOfReceive);
        sb.append(", dataOfCompletion=").append(dataOfCompletion);
        sb.append(", customerEmail='").append(customerEmail).append('\'');
        sb.append(", orderItemList=").append(orderItemList);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", itemAmount=").append(itemAmount);
        sb.append(", signPayment=").append(signPayment);
        sb.append(", paymentDate=").append(paymentDate);
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }

    private String getNumber() {
        Random random = new Random();
        Integer number = random.nextInt(1000000000);
        return Integer.toString(number);
    }
}
