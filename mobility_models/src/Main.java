import mobility_models.MobilityModel;
import mobility_models.constraints.gridwalk.Gridwalk;
import mobility_models.properties.swarm.TCR_swarm;

public class Main {
    public static void main(String[] args) {
        new MobilityModel(new TCR_swarm(), new Gridwalk());
    }
}
