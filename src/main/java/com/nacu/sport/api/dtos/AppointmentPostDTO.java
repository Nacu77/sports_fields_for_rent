package com.nacu.sport.api.dtos;

import com.nacu.sport.model.Appointment;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentPostDTO
{
    private String id;

    @NotNull(message = "Appointment is null")
    private Appointment appointment;

    @NotNull(message = "Slots is null")
    private int slots;

    private List<String> applicants;
}
