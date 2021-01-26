import mobility_models.MobilityModel;
import mobility_models.constraints.gridwalk.Gridwalk;
import mobility_models.constraints.racetrack.Racetrack;
import mobility_models.properties.swarm.TCR_swarm;
import mobility_models.usage.distributed.nodes.FloodNode;

public class Main {
    public static void main(String[] args) {
        new MobilityModel(new TCR_swarm(), new Gridwalk());
    }
}
