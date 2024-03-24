package com.pratice.dto;

public record ProductDto(
        String uuid,
        String name,
        double price,
        Integer qty) {
}
