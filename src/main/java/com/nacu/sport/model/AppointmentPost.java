package com.nacu.sport.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "appointment_posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentPost
{
    @Id
    private String id;

    private Appointment appointment;

    private int slots;

    private List<String> applicants;
}
