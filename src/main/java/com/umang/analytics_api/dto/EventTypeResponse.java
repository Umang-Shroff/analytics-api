package com.umang.analytics_api.dto;

public record EventTypeResponse(
        String eventType,
        long count
) {}