package com.umang.analyticsapi.dto;

public record HourlyResponse(
        String hour,
        long count
) {}