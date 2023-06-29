package com.nacu.sport.api.dtos;

import com.nacu.sport.api.constraints.ScheduleValidation;
import jakarta.validation.Valid;
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

    @NotBlank(message = "Empty name")
    @NotNull(message = "Name is null")
    private String name;

    private String description;

    @NotNull(message = "Price per hour is null")
    private Double pricePerHour;

    @Valid
    @NotNull(message = "Address is null")
    private AddressDTO address;

    @Min(value = 0, message = "Rating should be greater than 0 and lower than 5")
    @Max(value = 5, message = "Rating should be greater than 0 and lower than 5")
    private Double rating;

    @ScheduleValidation
    private ScheduleDTO schedule;
}
