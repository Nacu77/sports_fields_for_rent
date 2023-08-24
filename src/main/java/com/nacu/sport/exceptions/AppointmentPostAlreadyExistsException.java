package com.nacu.sport.exceptions;

public class AppointmentPostAlreadyExistsException extends RuntimeException
{
    public AppointmentPostAlreadyExistsException()
    {
        super("A post for this appointment already exists");
    }
}
