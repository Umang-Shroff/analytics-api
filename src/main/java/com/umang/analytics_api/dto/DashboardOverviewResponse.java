package com.umang.analyticsapi.dto;

public record DashboardOverviewResponse(
        long totalEvents,
        long totalTenants,
        long totalRevenue,
        long totalUsers
) {}