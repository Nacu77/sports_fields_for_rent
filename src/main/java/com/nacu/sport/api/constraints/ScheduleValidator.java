package com.nacu.sport.api.constraints;

import com.nacu.sport.api.dtos.ScheduleDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalTime;

public class ScheduleValidator implements ConstraintValidator<ScheduleValidation, ScheduleDTO>
{
    @Override
    public boolean isValid(ScheduleDTO scheduleDTO, ConstraintValidatorContext constraintValidatorContext)
    {
        if (scheduleDTO == null)
        {
            return true;
        }

        return
                isStartTimeBeforeEndTime(scheduleDTO.getMondayStart(), scheduleDTO.getMondayEnd()) &&
                isStartTimeBeforeEndTime(scheduleDTO.getTuesdayStart(), scheduleDTO.getTuesdayEnd()) &&
                isStartTimeBeforeEndTime(scheduleDTO.getWednesdayStart(), scheduleDTO.getWednesdayEnd()) &&
                isStartTimeBeforeEndTime(scheduleDTO.getThursdayStart(), scheduleDTO.getThursdayEnd()) &&
                isStartTimeBeforeEndTime(scheduleDTO.getFridayStart(), scheduleDTO.getFridayEnd()) &&
                isStartTimeBeforeEndTime(scheduleDTO.getSaturdayStart(), scheduleDTO.getSaturdayEnd()) &&
                isStartTimeBeforeEndTime(scheduleDTO.getSundayStart(), scheduleDTO.getSundayEnd());
    }

    private boolean isStartTimeBeforeEndTime(LocalTime startTime, LocalTime endTime)
    {
        // if both of them are not set then it's ok
        if (startTime == null && endTime == null)
        {
            return true;
        }

        // if one of them is set but the other one is not then it's not ok
        if (startTime == null || endTime == null)
        {
            return false;
        }

        // start time should be before end time
        return startTime.isBefore(endTime);
    }
}
