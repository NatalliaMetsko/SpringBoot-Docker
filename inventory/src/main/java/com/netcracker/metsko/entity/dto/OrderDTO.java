package com.netcracker.metsko.entity.dto;


import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {


    private Long id;

    private String name;

    private String customerEmail;

    private LocalDate dataOfCreation;

    private double totalPrice;

    private int itemAmount;

    private String status;

    private List<OrderItemDTO> orderItemList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDate getDataOfCreation() {
        return dataOfCreation;
    }

    public void setDataOfCreation(LocalDate dataOfCreation) {
        this.dataOfCreation = dataOfCreation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
