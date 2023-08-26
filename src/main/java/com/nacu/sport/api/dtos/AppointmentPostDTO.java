package com.nacu.sport.api.dtos;

import com.nacu.sport.api.constraints.validations.AppointmentPostValidation;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AppointmentPostValidation
public class AppointmentPostDTO
{
    private String id;

    @NotNull(message = "Appointment is null")
    private AppointmentDTO appointment;

    @NotNull(message = "Slots is null")
    private int slots;

    private List<String> applicants;
}
