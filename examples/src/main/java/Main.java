import io.jbotsim.core.Topology;
import io.jbotsim.ui.JViewer;
import mobility_models.MobilityModel;
import mobility_models.constraints.gridwalk.Gridwalk;
import mobility_models.constraints.racetrack.Racetrack;
import mobility_models.properties.roundabout.EB_roundabout;
import mobility_models.properties.swarm.TCR_swarm;

public class Main {
    public static void main(String[] args) {
        MobilityModel mobilityModel = new MobilityModel(new EB_roundabout(), new Racetrack());
        Topology tp = mobilityModel.getTopology();
        new JViewer(tp);
        tp.start();
        //tp.pause();
    }
}
