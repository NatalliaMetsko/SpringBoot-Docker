package com.netcracker.metsko.entity.dto;


import lombok.Data;

@Data
public class OfferDTO {

    private Long id;

    private String name;

    private String description;

    private double price;
}
