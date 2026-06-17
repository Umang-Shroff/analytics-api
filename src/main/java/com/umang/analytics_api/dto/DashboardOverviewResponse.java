package com.umang.analyticsapi.dto;

public record DashboardOverviewResponse(
        long totalEvents,
        long totalUsers,
        long totalTenants,
        long totalRevenue
) {}