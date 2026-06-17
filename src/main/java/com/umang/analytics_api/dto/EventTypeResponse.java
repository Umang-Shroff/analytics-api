package com.umang.analyticsapi.dto;

public record EventTypeResponse(
        String eventType,
        long count
) {}