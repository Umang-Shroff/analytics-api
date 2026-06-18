package com.umang.analytics_api.dto;

public record PartitionResponse(
        int partitionId,
        long events
) {}