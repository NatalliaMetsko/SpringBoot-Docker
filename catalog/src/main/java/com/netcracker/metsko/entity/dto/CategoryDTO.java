package com.netcracker.metsko.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDTO {

    private String category;

    private List<OfferDTO> dtoList;
}
