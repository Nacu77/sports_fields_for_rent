package com.nacu.sport.repositories;

import com.nacu.sport.model.SportField;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportFieldRepository extends ElasticsearchRepository<SportField, String>
{
    List<SportField> findAllByCreatedBy(String createdBy);
}
