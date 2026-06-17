package com.umang.analyticsapi.dto;

public record CampaignResponse(
        String campaignId,
        long clicks
) {}