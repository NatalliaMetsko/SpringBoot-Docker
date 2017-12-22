package com.netcracker.metsko.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.netcracker.metsko.util.LocalDateDeserializer;
import com.netcracker.metsko.util.LocalDateSerializer;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO {


    private Long id;

    private String name;

    private String customerEmail;

    private double totalPrice;

    private int itemAmount;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataOfCreation;

    private String status;

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
