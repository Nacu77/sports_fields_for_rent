package com.nacu.sport.api.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportFieldDTO
{
    private String id;

    @NotBlank(message = "Invalid name: Empty name")
    @NotNull(message = "Invalid name: Name is null")
    private String name;

    private String description;

    @NotNull(message = "Invalid price per hour: Price per hour is null")
    private Double pricePerHour;

    @NotNull(message = "Invalid address: Address is null")
    private AddressDTO address;

    @Min(value = 0, message = "Invalid rating: rating should be greater than 0 and lower than 5")
    @Max(value = 5, message = "Invalid rating: rating should be greater than 0 and lower than 5")
    private Double rating;
}
