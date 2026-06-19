package com.umang.analytics_api.repository;

import org.springframework.stereotype.Repository;

import com.umang.analytics_api.dto.*;

import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class AnalyticsRepository {

    private final Connection connection;

    public AnalyticsRepository(Connection connection) {
        this.connection = connection;
    }

    public long getTotalEvents() {

        String sql =
                """
                SELECT count(*)
                FROM events
                """;

        try(
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ){

            rs.next();
            return rs.getLong(1);

        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public long getTotalUsers() {

        String sql =
                """
                SELECT count(DISTINCT userId)
                FROM events
                """;

        try(
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            rs.next();
            return rs.getLong(1);

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserActivityResponse> getTopUsers() {

        String sql =
                """
                SELECT
                    userId,
                    count(*) AS events
                FROM events
                GROUP BY userId
                ORDER BY events DESC
                LIMIT 20
                """;

        List<UserActivityResponse> result = new ArrayList<>();

        try(
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while(rs.next()) {
                result.add(
                        new UserActivityResponse(
                                rs.getString("userId"),
                                rs.getLong("events")
                        )
                );
            }

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<DeviceResponse> getDeviceAnalytics() {

        String sql =
                """
                SELECT
                    device,
                    count(*) AS events
                FROM events
                GROUP BY device
                ORDER BY events DESC
                """;

        List<DeviceResponse> result = new ArrayList<>();

        try(
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while(rs.next()) {
                result.add(
                        new DeviceResponse(
                                rs.getString("device"),
                                rs.getLong("events")
                        )
                );
            }

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public long getTotalTenants() {

        String sql =
                """
                SELECT count(DISTINCT tenantId)
                FROM events
                """;

        try(
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            rs.next();
            return rs.getLong(1);

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public long getTotalRevenue() {

        String sql =
                """
                SELECT sum(revenue)
                FROM revenue_stats
                """;

        try(
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            rs.next();
            return rs.getLong(1);

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    // public long getPurchaseCount() {

    //     String sql =
    //             """
    //             SELECT count(*)
    //             FROM events
    //             WHERE eventType = 'PURCHASE'
    //             """;

    //     try(
    //             PreparedStatement stmt = connection.prepareStatement(sql);
    //             ResultSet rs = stmt.executeQuery()
    //     ) {

    //         rs.next();
    //         return rs.getLong(1);

    //     } catch(Exception e) {
    //         throw new RuntimeException(e);
    //     }
    // }

    // public double getAverageOrderValue() {

    //     long revenue = getTotalRevenue();
    //     long purchases = getPurchaseCount();
    //     if(purchases == 0){
    //         return 0;
    //     }
    //     return (double) revenue / purchases;
    // }

    public List<EventTypeResponse> getEventTypes() {

        String sql =
                """
                SELECT
                    eventType,
                    sum(count) AS total
                FROM event_type_counts
                GROUP BY eventType
                ORDER BY total DESC
                """;

        List<EventTypeResponse> result = new ArrayList<>();

        try(
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while(rs.next()) {
                result.add(new EventTypeResponse(rs.getString("eventType"), rs.getLong("total")));
            }

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public RevenueResponse getRevenueAnalytics() {

        String sql = 
        """
            SELECT
                count(*) as purchaseCount,
                sum(amount) as totalRevenue
            FROM events
            WHERE eventType='PURCHASE'
        """;

        try (
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
        
            rs.next();
            long purchaseCount = rs.getLong("purchaseCount");
            long totalRevenue = rs.getLong("totalRevenue");
    
            double averageOrderValue =
                    purchaseCount == 0
                            ? 0
                            : (double) totalRevenue / purchaseCount;
    
            // System.out.println("==============================================================================");
            // System.out.println("Purchase Count: " + purchaseCount + "Average order value: " + averageOrderValue);
            // System.out.println("==============================================================================");
            return new RevenueResponse(
                    totalRevenue,
                    purchaseCount,
                    averageOrderValue
            );
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TenantResponse> getTenantAnalytics() {

        String sql =
                """
                SELECT
                    tenantId,
                    sum(count) AS total
                FROM tenant_event_counts
                GROUP BY tenantId
                ORDER BY total DESC
                """;

        List<TenantResponse> result = new ArrayList<>();

        try(
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while(rs.next()) {
                result.add(new TenantResponse(rs.getString("tenantId"),rs.getLong("total")));
            }

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<CampaignResponse> getCampaignAnalytics() {

        String sql =
                """
                SELECT
                    campaignId,
                    sum(clicks) AS total
                FROM campaign_stats
                GROUP BY campaignId
                ORDER BY total DESC
                LIMIT 20
                """;

        List<CampaignResponse> result = new ArrayList<>();

        try(
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while(rs.next()) {
                result.add(
                        new CampaignResponse(
                                rs.getString("campaignId"),
                                rs.getLong("total")
                        )
                );
            }

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<HourlyResponse> getHourlyAnalytics() {

        String sql =
                """
                SELECT
                    toString(
                        toStartOfHour(eventTimestamp)
                    ) AS hour,
                    count(*) AS total
                FROM events
                GROUP BY hour
                ORDER BY hour
                """;

        List<HourlyResponse> result = new ArrayList<>();

        try(
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while(rs.next()) {
                result.add(
                        new HourlyResponse(
                                rs.getString("hour"),
                                rs.getLong("total")
                        )
                );
            }

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<PartitionResponse> getPartitionAnalytics() {

        String sql =
                """
                SELECT
                    partitionId,
                    count(*) AS total
                FROM events
                GROUP BY partitionId
                ORDER BY partitionId
                """;

        List<PartitionResponse> result = new ArrayList<>();

        try(
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while(rs.next()) {
                result.add(
                        new PartitionResponse(
                                rs.getInt("partitionId"),
                                rs.getLong("total")
                        )
                );
            }

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    
}