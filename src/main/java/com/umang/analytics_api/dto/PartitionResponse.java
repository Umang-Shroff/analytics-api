package com.umang.analyticsapi.dto;

public record PartitionResponse(
        int partitionId,
        long events
) {}