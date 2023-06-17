package com.nacu.sport.api.dtos;

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
public class AddressDTO
{
    @NotBlank(message = "Empty country")
    @NotNull(message = "Country is null")
    private String country;

    @NotBlank(message = "Empty city")
    @NotNull(message = "City is null")
    private String city;

    @NotBlank(message = "Empty street")
    @NotNull(message = "Street is null")
    private String street;

    @NotNull(message = "Number is null")
    @Min(value = 1, message = "Number should be greater than 0")
    private Integer number;
}
