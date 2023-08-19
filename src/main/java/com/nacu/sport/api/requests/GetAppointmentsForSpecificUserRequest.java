package com.nacu.sport.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAppointmentsForSpecificUserRequest
{
    @NotNull(message = "Username is null")
    private String username;

    // flag to get current appointments (true) or history of appointments (false)
    @NotNull(message = "Current flag is null")
    @JsonProperty
    private boolean isCurrent;
}
