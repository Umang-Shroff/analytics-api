@Service
public class DashboardService {

    private final AnalyticsRepository repository;

    public DashboardService(AnalyticsRepository repository){
        this.repository = repository;
    }

    public DashboardOverviewResponse getOverview(){
        return new DashboardOverviewResponse(
                repository.getTotalEvents(),
                repository.getTotalUsers(),
                repository.getTotalTenants(),
                repository.getTotalRevenue()
        );
    }
}