@Service
public class DashboardService {

    private final AnalyticsRepository repository;

    public DashboardService(AnalyticsRepository repository){
        this.repository = repository;
    }

    public DashboardOverviewResponse getOverview(){
        return new DashboardOverviewResponse(
                repository.getTotalEvents(),
                0,
                0,
                0
        );
    }
}