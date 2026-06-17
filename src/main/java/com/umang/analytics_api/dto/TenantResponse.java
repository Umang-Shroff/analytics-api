package com.umang.analyticsapi.dto;

public record TenantResponse(
        String tenantId,
        long events
) {}