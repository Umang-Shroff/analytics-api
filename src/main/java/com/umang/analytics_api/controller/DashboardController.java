package com.umang.analytics_api.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.umang.analytics_api.service.DashboardService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.umang.analytics_api.dto.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service){
        this.service = service;
    }

    @GetMapping("/overview")
    public DashboardOverviewResponse overview(){
        return service.getOverview();
    }

    @GetMapping("/event-types")
    public List<EventTypeResponse> eventTypes() {
        return service.getEventTypes();
    }
    
    @GetMapping("/revenue")
    public RevenueResponse revenue() {
        return service.getRevenueAnalytics();
    }
    
    @GetMapping("/tenants")
    public List<TenantResponse> tenants() {
        return service.getTenantAnalytics();
    }
    
    @GetMapping("/campaigns")
    public List<CampaignResponse> campaigns() {
        return service.getCampaignAnalytics();
    }
    
    @GetMapping("/hourly")
    public List<HourlyResponse> hourly() {
        return service.getHourlyAnalytics();
    }
    
    @GetMapping("/partitions")
    public List<PartitionResponse> partitions() {
        return service.getPartitionAnalytics();
    }

    @GetMapping("/health")
    public String health(){
        return "Connected to SpringBoot";
    }
}