package com.nacu.sport.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com/nacu/sport/repositories")
public class ElasticsearchConfig
{
}
