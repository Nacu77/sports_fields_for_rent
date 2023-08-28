package com.nacu.sport.repositories;

import com.nacu.sport.model.Appointment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends ElasticsearchRepository<Appointment, String>
{
    List<Appointment> findAllBySportFieldIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualOrderByStartDateTimeAsc(String sportFieldId, LocalDateTime startDate, LocalDateTime endDate);
    List<Appointment> findAllByCreatedByAndEndDateTimeGreaterThanOrderByStartDateTimeAsc(String createdBy, LocalDateTime endDate);
    List<Appointment> findAllByCreatedByAndEndDateTimeLessThanOrderByStartDateTimeAsc(String createdBy, LocalDateTime endDate);
    List<Appointment> findAllBySportFieldIdAndEndDateTimeGreaterThanOrderByStartDateTimeAsc(String sportFieldId, LocalDateTime endDate);
    List<Appointment> findAllBySportFieldIdAndEndDateTimeLessThanOrderByStartDateTimeAsc(String sportFieldId, LocalDateTime endDate);
    void deleteAllBySportFieldIdAndEndDateTimeGreaterThan(String sportFieldId, LocalDateTime endDate);
}
