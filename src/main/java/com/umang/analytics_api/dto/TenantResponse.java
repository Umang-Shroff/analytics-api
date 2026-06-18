package com.umang.analytics_api.dto;

public record TenantResponse(
        String tenantId,
        long events
) {}