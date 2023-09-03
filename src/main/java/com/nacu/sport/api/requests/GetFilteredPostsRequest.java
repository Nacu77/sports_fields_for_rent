package com.nacu.sport.api.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetFilteredPostsRequest
{
    private Double minPrice;
    private Double maxPrice;
    private String country;
    private String city;
    private String name;
    private String type;
}
