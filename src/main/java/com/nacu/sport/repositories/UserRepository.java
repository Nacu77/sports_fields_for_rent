package com.nacu.sport.repositories;

import com.nacu.sport.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String>
{
    User findByUsername(String username);
}
