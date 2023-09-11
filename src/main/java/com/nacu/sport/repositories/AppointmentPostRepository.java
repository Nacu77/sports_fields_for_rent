package com.nacu.sport.repositories;

import com.nacu.sport.model.AppointmentPost;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentPostRepository extends ElasticsearchRepository<AppointmentPost, String>
{
    @Query("""
            {
                "bool": {
                    "must": [
                        { "range": { "appointment.startDateTime": {"gte": "?0"} }}
                    ]
                }
            }
            """)
    List<AppointmentPost> findAll(LocalDateTime startDateTime, Sort sort);

    @Query("""
            {
                "bool": {
                    "must": [
                        { "match": { "appointment.createdBy": "?0" }},
                        { "range": { "appointment.startDateTime": {"gte": "?1"} }}
                    ]
                }
            }
            """)
    List<AppointmentPost> findAllByUsername(String username, LocalDateTime startDateTime, Sort sort);

    @Query("""
            {
                "bool": {
                    "must": [
                        { "match": { "applicants": "?0" }},
                        { "range": { "appointment.startDateTime": {"gte": "?1"} }}
                    ]
                }
            }
            """)
    List<AppointmentPost> findAllByUsernameInApplicants(String username, LocalDateTime startDateTime, Sort sort);

    @Query("""
            {
                "bool": {
                    "must": [{
                        "match": { "appointment.id": "?0" }
                    }]
                }
            }
            """)
    Optional<AppointmentPost> findByAppointmentId(String appointmentId);

    @Query("""
            {
                "bool": {
                    "must": [
                        { "match": { "appointment.sportFieldId": "?0" }},
                        { "range": { "appointment.startDateTime": {"gte": "?1"} }}
                    ]
                }
            }
            """)
    List<AppointmentPost> findAllBySportFieldId(String sportFieldId, LocalDateTime startDateTime);
}
