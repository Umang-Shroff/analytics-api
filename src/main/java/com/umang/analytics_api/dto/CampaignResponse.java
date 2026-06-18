package com.umang.analytics_api.dto;

public record CampaignResponse(
        String campaignId,
        long clicks
) {}