package com.nacu.sport.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleDTO
{
    private LocalTime mondayStart;
    private LocalTime mondayEnd;

    private LocalTime tuesdayStart;
    private LocalTime tuesdayEnd;

    private LocalTime wednesdayStart;
    private LocalTime wednesdayEnd;

    private LocalTime thursdayStart;
    private LocalTime thursdayEnd;

    private LocalTime fridayStart;
    private LocalTime fridayEnd;

    private LocalTime saturdayStart;
    private LocalTime saturdayEnd;

    private LocalTime sundayStart;
    private LocalTime sundayEnd;
}
