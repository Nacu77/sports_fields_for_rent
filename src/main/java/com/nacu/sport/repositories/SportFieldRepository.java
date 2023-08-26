package com.nacu.sport.repositories;

import com.nacu.sport.model.SportField;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportFieldRepository extends ElasticsearchRepository<SportField, String>
{
    List<SportField> findAllByCreatedBy(String createdBy);

    @Query("""
            {
                "bool": {
                    "must": [
                        { "range": { "pricePerHour": {"gte": "?0", "lte": "?1"} }},
                        { "wildcard": { "address.country": { "value" : "?2", "case_insensitive" : "true" } }},
                        { "wildcard": { "address.city": { "value" : "?3", "case_insensitive" : "true" } }},
                        { "wildcard": { "name": { "value" : "?4", "case_insensitive" : "true" } }}
                    ]
                }
            }
            """)
    List<SportField> findAllFiltered(Double minPrice, Double maxPrice, String country, String city, String name);
}
