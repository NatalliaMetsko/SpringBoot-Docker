package com.netcracker.metsko.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.netcracker.metsko.util.LocalDateDeserializer;
import com.netcracker.metsko.util.LocalDateSerializer;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

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

    private List<OrderItemDTO> orderItemList;

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
