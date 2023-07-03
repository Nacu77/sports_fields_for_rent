package com.nacu.sport.repositories;

import com.nacu.sport.model.Appointment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends ElasticsearchRepository<Appointment, String>
{
}
