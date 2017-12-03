package com.netcracker.metsko.entity;


import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class OrderDTO {

    private Long id;

    private String name;

    private String description;

    private double totalPrice;

    private int itemAmount;

    private LocalDate dataOfCreation;

    private LocalDate dataOfComplete;

    private boolean signPayment;

    private boolean status;

}
