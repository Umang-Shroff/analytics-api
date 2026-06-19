package com.umang.analytics_api.dto;

public record UserActivityResponse(
        String userId,
        long events
) {}