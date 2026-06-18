package com.umang.analytics_api.dto;

public record RevenueResponse(
        long totalRevenue,
        long purchaseCount,
        double averageOrderValue
) {}