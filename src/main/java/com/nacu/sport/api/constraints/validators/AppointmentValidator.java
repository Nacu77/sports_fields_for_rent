package com.nacu.sport.api.constraints.validators;

import com.nacu.sport.api.constraints.validations.AppointmentValidation;
import com.nacu.sport.api.dtos.AppointmentDTO;
import com.nacu.sport.model.Schedule;
import com.nacu.sport.model.SportField;
import com.nacu.sport.repositories.SportFieldRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppointmentValidator implements ConstraintValidator<AppointmentValidation, AppointmentDTO>
{
    @Autowired
    private SportFieldRepository sportFieldRepository;

    @Override
    public boolean isValid(AppointmentDTO appointmentDTO, ConstraintValidatorContext constraintValidatorContext)
    {
        Schedule schedule = getSchedule(appointmentDTO.getSportFieldId());

        // it's not valid if there is no schedule
        if (schedule == null)
        {
            return false;
        }

        LocalDateTime startDate = appointmentDTO.getStartDateTime();
        LocalDateTime endDate = appointmentDTO.getEndDateTime();

        // it's not valid if start date is after end date
        if (startDate.isAfter(endDate))
        {
            return false;
        }

        // it's not valid if appointment is in the past
        if (startDate.isBefore(LocalDateTime.now()))
        {
            return false;
        }

        // check if the time fits the schedule
        switch (appointmentDTO.getStartDateTime().getDayOfWeek())
        {
            case MONDAY ->
            {
                return isOnSchedule(startDate, endDate, schedule.getMondayStart(), schedule.getMondayEnd());
            }
            case TUESDAY ->
            {
                return isOnSchedule(startDate, endDate, schedule.getTuesdayStart(), schedule.getTuesdayEnd());
            }
            case WEDNESDAY ->
            {
                return isOnSchedule(startDate, endDate, schedule.getWednesdayStart(), schedule.getWednesdayEnd());
            }
            case THURSDAY ->
            {
                return isOnSchedule(startDate, endDate, schedule.getThursdayStart(), schedule.getThursdayEnd());
            }
            case FRIDAY ->
            {
                return isOnSchedule(startDate, endDate, schedule.getFridayStart(), schedule.getFridayEnd());
            }
            case SATURDAY ->
            {
                return isOnSchedule(startDate, endDate, schedule.getSaturdayStart(), schedule.getSaturdayEnd());
            }
            case SUNDAY ->
            {
                return isOnSchedule(startDate, endDate, schedule.getSundayStart(), schedule.getSundayEnd());
            }
            default ->
            {
                return false;
            }
        }
    }

    private Schedule getSchedule(String sportFieldId)
    {
        SportField sportField = sportFieldRepository.findById(sportFieldId).orElse(null);
        if (sportField == null || sportField.getSchedule() == null)
        {
            return null;
        }

        return sportField.getSchedule();
    }

    private boolean isOnSchedule(LocalDateTime startDate, LocalDateTime endDate, LocalTime startTime, LocalTime endTime)
    {
        if (startTime == null || endTime == null)
        {
            return false;
        }

        if (startDate.getHour() < startTime.getHour())
        {
            return false;
        }

        if (startDate.getHour() == startTime.getHour() && startDate.getMinute() < startTime.getMinute())
        {
            return false;
        }

        if (endDate.getHour() > endTime.getHour())
        {
            return false;
        }

        if (endDate.getHour() == endTime.getHour() && endDate.getMinute() > endTime.getMinute())
        {
            return false;
        }

        return true;
    }
}
