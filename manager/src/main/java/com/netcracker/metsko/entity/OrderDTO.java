package com.netcracker.metsko.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO {

    private Long id;

    private String name;

    private String description;

    private double totalPrice;

    private int itemAmount;

    private LocalDate dataOfCreation;

    private String status;

}
