package com.netcracker.metsko.entity;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OfferDTO {

    private Long id;

    private String name;

    private String description;

    private double price;
}
