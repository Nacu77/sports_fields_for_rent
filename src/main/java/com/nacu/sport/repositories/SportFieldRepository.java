package com.nacu.sport.repositories;

import com.nacu.sport.model.SportField;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportFieldRepository extends ElasticsearchRepository<SportField, String>
{
}
