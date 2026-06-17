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
}