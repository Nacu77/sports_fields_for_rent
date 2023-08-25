package com.nacu.sport.api.constraints.validators;

import com.nacu.sport.api.constraints.validations.AppointmentPostValidation;
import com.nacu.sport.api.dtos.AppointmentPostDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AppointmentPostValidator implements ConstraintValidator<AppointmentPostValidation, AppointmentPostDTO>
{
    @Override
    public boolean isValid(AppointmentPostDTO appointmentPostDTO, ConstraintValidatorContext constraintValidatorContext)
    {
        if (appointmentPostDTO.getApplicants() == null || appointmentPostDTO.getApplicants().isEmpty())
        {
            return true;
        }

        return appointmentPostDTO.getSlots() >= appointmentPostDTO.getApplicants().size();
    }
}
