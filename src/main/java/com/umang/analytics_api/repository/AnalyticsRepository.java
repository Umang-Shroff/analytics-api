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
}