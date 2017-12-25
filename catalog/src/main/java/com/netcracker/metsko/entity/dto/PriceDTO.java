package com.netcracker.metsko.entity.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceDTO {

    private String currency;

    private Double price;
}
