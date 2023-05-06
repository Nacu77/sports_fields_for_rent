package com.nacu.sport.exceptions;

public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException(String id)
    {
        super("Resource with id " + id + " not found");
    }
}
