package com.nacu.sport.api.constraints.validators;

import com.nacu.sport.api.constraints.validations.AppointmentValidation;
import com.nacu.sport.api.dtos.AppointmentDTO;
import com.nacu.sport.model.Schedule;
import com.nacu.sport.model.SportField;
import com.nacu.sport.repositories.AppointmentRepository;
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

    @Autowired
    private AppointmentRepository appointmentRepository;

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
        boolean fitsTheSchedule;
        switch (appointmentDTO.getStartDateTime().getDayOfWeek())
        {
            case MONDAY -> fitsTheSchedule = isOnSchedule(startDate, endDate, schedule.getMondayStart(), schedule.getMondayEnd());
            case TUESDAY -> fitsTheSchedule = isOnSchedule(startDate, endDate, schedule.getTuesdayStart(), schedule.getTuesdayEnd());
            case WEDNESDAY -> fitsTheSchedule = isOnSchedule(startDate, endDate, schedule.getWednesdayStart(), schedule.getWednesdayEnd());
            case THURSDAY -> fitsTheSchedule = isOnSchedule(startDate, endDate, schedule.getThursdayStart(), schedule.getThursdayEnd());
            case FRIDAY -> fitsTheSchedule = isOnSchedule(startDate, endDate, schedule.getFridayStart(), schedule.getFridayEnd());
            case SATURDAY -> fitsTheSchedule = isOnSchedule(startDate, endDate, schedule.getSaturdayStart(), schedule.getSaturdayEnd());
            case SUNDAY -> fitsTheSchedule = isOnSchedule(startDate, endDate, schedule.getSundayStart(), schedule.getSundayEnd());
            default -> fitsTheSchedule = false;
        }
        if (!fitsTheSchedule)
        {
            return false;
        }

        // check if the time overlaps other appointments
        boolean overlapsOtherAppointment = appointmentRepository
                .findAllBySportFieldIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualOrderByStartDateTimeAsc(
                    appointmentDTO.getSportFieldId(),
                    startDate.toLocalDate().atStartOfDay(),
                    endDate.toLocalDate().atTime(LocalTime.MAX)
                )
                .stream()
                .anyMatch(appointment ->
                        (startDate.isAfter(appointment.getStartDateTime()) && startDate.isBefore(appointment.getEndDateTime())) ||
                        (endDate.isAfter(appointment.getStartDateTime()) && endDate.isBefore(appointment.getEndDateTime())) ||
                        (startDate.isBefore(appointment.getStartDateTime()) && endDate.isAfter(appointment.getEndDateTime()))
                );

        return !overlapsOtherAppointment;
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
