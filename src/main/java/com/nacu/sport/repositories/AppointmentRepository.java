package com.nacu.sport.repositories;

import com.nacu.sport.model.Appointment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends ElasticsearchRepository<Appointment, String>
{
    List<Appointment> findAllBySportFieldIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqual(String sportFieldId, LocalDateTime startDate, LocalDateTime endDate);
    List<Appointment> findAllByCreatedByAndEndDateTimeGreaterThan(String createdBy, LocalDateTime endDate);
    List<Appointment> findAllByCreatedByAndEndDateTimeLessThan(String createdBy, LocalDateTime endDate);
}
