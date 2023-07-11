package com.nacu.sport.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nacu.sport.api.constraints.validations.AppointmentValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AppointmentValidation
public class AppointmentDTO
{
    private String id;

    @NotBlank(message = "Empty sport field id")
    @NotNull(message = "Sport field id is null")
    private String sportFieldId;

    @NotNull(message = "Start date is null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDateTime;

    @NotNull(message = "End date is null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDateTime;
}
