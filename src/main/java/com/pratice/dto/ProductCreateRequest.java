package com.pratice.dto;

import jakarta.validation.constraints.*;

public record ProductCreateRequest(
        @NotBlank
        @NotNull
        @NotEmpty
        String name,
        @Positive
        @NotNull
        Double price,
        @Positive
        @NotNull
        @Max(100)
        Integer qty
) {

}
