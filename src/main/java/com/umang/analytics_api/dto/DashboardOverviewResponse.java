package com.umang.analytics_api.dto;

public record DashboardOverviewResponse(
        long totalEvents,
        long totalUsers,
        long totalTenants,
        long totalRevenue
) {}