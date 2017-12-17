package com.netcracker.metsko.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.netcracker.metsko.entity.enums.Status;
import com.netcracker.metsko.util.LocalDateDeserializer;
import com.netcracker.metsko.util.LocalDateSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity(name = "InvOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    @Size(min = 5, max = 100)
    private String description;

    @Column
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataOfOrder;

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

    public Order(String name, String description, LocalDate dataOfOrder, LocalDate dataOfReceive,
                 LocalDate dataOfCompletion, String customerEmail, List<OrderItem> orderItemList,
                 double totalPrice, int itemAmount, LocalDate paymentDate, String status) {
        this.name = name;
        this.description = description;
        this.dataOfOrder = dataOfOrder;
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

    private void setName() {
        this.name = "Order#" + getId();
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

    private void setDataOfOrder() {
        this.dataOfOrder = LocalDate.now();
    }

    public LocalDate getDataOfReceive() {
        return dataOfReceive;
    }

    private void setDataOfReceive() {
        if (this.paymentDate != null) {
            this.dataOfReceive = paymentDate.plusDays(7);
            this.status = String.valueOf(Status.ACTIVE);
        } else {
            this.dataOfReceive = null;
        }
    }

    public LocalDate getDataOfCompletion() {
        return dataOfCompletion;
    }

    private void setDataOfCompletion() {
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
        setTotalPrice();
        return totalPrice;
    }

    private void setTotalPrice() {
        this.totalPrice = this.orderItemList.stream().mapToDouble(OrderItem::getPrice).sum();
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount() {
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

    private void setPaymentDate() {
        if (this.signPayment) {
            this.paymentDate = LocalDate.now();
        } else {
            this.paymentDate = null;
        }
    }

    public String getStatus() {
        setStatus();
        return status;
    }

    private void setStatus() {
        if (orderItemList.size() == 0) {
            this.status = String.valueOf(Status.NOT_AVAILABLE);
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

    public void addOrderItem(OrderItem orderItem) {
        this.orderItemList.add(orderItem);
        orderItem.setOrder(this);
    }

    public void removeOrderItem(OrderItem orderItem) {
        this.orderItemList.remove(orderItem);
    }

    public void setFields() {
        setName();
        setItemAmount();
        setDataOfOrder();
        setDataOfReceive();
        setDataOfCompletion();
        setTotalPrice();
        setPaymentDate();
        setStatus();

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
                Objects.equals(getDescription(), order.getDescription()) &&
                Objects.equals(getDataOfOrder(), order.getDataOfOrder()) &&
                Objects.equals(getDataOfReceive(), order.getDataOfReceive()) &&
                Objects.equals(getDataOfCompletion(), order.getDataOfCompletion()) &&
                Objects.equals(getCustomerEmail(), order.getCustomerEmail()) &&
                Objects.equals(getOrderItemList(), order.getOrderItemList()) &&
                Objects.equals(getPaymentDate(), order.getPaymentDate()) &&
                Objects.equals(getStatus(), order.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getDataOfOrder(), getDataOfReceive(), getDataOfCompletion(), getCustomerEmail(), getOrderItemList(), getTotalPrice(), getItemAmount(), getSignPayment(), getPaymentDate(), getStatus());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", dataOfOrder=").append(dataOfOrder);
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
}
