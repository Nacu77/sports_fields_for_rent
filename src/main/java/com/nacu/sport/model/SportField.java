package com.nacu.sport.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "fields")
@Data
public class SportField
{
    @Id
    private String id;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Double pricePerHour;

    @NotNull
    private Address address;

    @Min(value = 0)
    @Max(value = 5)
    private Double rating = 0d;
}
