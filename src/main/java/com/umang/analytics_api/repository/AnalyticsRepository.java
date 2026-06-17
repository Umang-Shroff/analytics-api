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
                SELECT sum(amount)
                FROM events
                WHERE amount > 0
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
                    sum(amount),
                    countIf(amount > 0),
                    avgIf(amount, amount > 0)
                FROM events
                """;

        try(
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            rs.next();
            return new RevenueResponse(
                    rs.getLong(1),
                    rs.getLong(2),
                    rs.getDouble(3)
            );

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TenantResponse> getTenantAnalytics() {

        String sql =
                """
                SELECT
                    tenantId,
                    count(*) AS total
                FROM events
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
                    count(*) AS clicks
                FROM events
                WHERE campaignId != ''
                GROUP BY campaignId
                ORDER BY clicks DESC
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
                                rs.getLong("clicks")
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