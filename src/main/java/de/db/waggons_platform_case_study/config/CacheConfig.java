package de.db.waggons_platform_case_study.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean("customKeyGenerator")
    public CustomKeyGenerator keyGenerator() {
        return new CustomKeyGenerator();
    }
}
