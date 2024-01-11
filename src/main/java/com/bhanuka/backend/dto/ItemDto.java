package com.bhanuka.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String name;

    private Double price;

    private Integer qty;

    private Long categoryId;
}
