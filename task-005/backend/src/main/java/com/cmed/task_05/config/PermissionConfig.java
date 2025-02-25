package com.cmed.task_05.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class PermissionConfig {
    final String[] PERMIT_ALL = {
            "/api/v1/auth/**",
            "/swagger-ui/**",
            "/swagger-ui.html/**",
            "/swagger-resources/**",
            "/api-docs/**",
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/api/v1/report",
            "/api/v1/external/data",
            "/h2-console/**"
    };
}
