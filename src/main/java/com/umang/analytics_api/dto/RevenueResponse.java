package com.umang.analyticsapi.dto;

public record RevenueResponse(
        long totalRevenue,
        long purchaseCount,
        double averageOrderValue
) {}