package com.umang.analytics_api.dto;

public record HourlyResponse(
        String hour,
        long count
) {}