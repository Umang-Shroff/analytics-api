package com.umang.analytics_api.service;

import org.springframework.stereotype.Service;

import com.umang.analytics_api.dto.*;
import com.umang.analytics_api.repository.AnalyticsRepository;

import java.util.List;

@Service
public class DashboardService {

    private final AnalyticsRepository repository;

    public DashboardService(AnalyticsRepository repository) {
        this.repository = repository;
    }

    public DashboardOverviewResponse getOverview() {

        return new DashboardOverviewResponse(
                repository.getTotalEvents(),
                repository.getTotalUsers(),
                repository.getTotalTenants(),
                repository.getTotalRevenue()
        );
    }

    public List<EventTypeResponse> getEventTypes() {
        return repository.getEventTypes();
    }

    public RevenueResponse getRevenueAnalytics() {
        return repository.getRevenueAnalytics();
    }

    public List<TenantResponse> getTenantAnalytics() {
        return repository.getTenantAnalytics();
    }

    public List<CampaignResponse> getCampaignAnalytics() {
        return repository.getCampaignAnalytics();
    }

    public List<HourlyResponse> getHourlyAnalytics() {
        return repository.getHourlyAnalytics();
    }

    public List<PartitionResponse> getPartitionAnalytics() {
        return repository.getPartitionAnalytics();
    }

    public List<UserActivityResponse> getTopUsers() {
        return repository.getTopUsers();
    }
    
    public List<DeviceResponse> getDeviceAnalytics() {
        return repository.getDeviceAnalytics();
    }
}