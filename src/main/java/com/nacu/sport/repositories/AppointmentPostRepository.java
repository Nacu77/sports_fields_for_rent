package com.nacu.sport.repositories;

import com.nacu.sport.model.AppointmentPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentPostRepository extends ElasticsearchRepository<AppointmentPost, String>
{
}
