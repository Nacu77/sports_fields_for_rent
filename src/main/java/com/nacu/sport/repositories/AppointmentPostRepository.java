package com.nacu.sport.repositories;

import com.nacu.sport.model.AppointmentPost;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentPostRepository extends ElasticsearchRepository<AppointmentPost, String>
{
    @Query("{\"bool\": " +
                "{\"must\": " +
                    "[{\"match\": " +
                        "{\"appointment.createdBy\": \"?0\"" +
            "}}]}}")
    List<AppointmentPost> findAllByUsername(String username);
}
