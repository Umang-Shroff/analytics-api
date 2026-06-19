package com.umang.analytics_api.dto; 

public record DeviceResponse(
        String device,
        long events
) {}