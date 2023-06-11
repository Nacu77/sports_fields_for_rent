package com.nacu.sport.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "fields")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportField
{
    @Id
    private String id;

    private String name;
    private String description;
    private Double pricePerHour;
    private Address address;
    private Double rating;
}
