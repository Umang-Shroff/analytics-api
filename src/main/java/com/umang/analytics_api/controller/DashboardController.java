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
}