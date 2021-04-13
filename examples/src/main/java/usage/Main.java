package usage;

import io.jbotsim.core.Topology;
import io.jbotsim.ui.JViewer;
import mobility_models.MobilityModel;
import mobility_models.constraints.Constraint;
import mobility_models.constraints.racetrack.*;
import mobility_models.properties.Property;
import mobility_models.properties.gridswarm.PR_gridswarm;
import usage.nodes.EliminationNode;

public class Main {

    public static void main(String[] args) {
        Property property;
//        property = new TCR_swarm();
//        property = new KR_swarm();
//        property = new KR_gridswarm();
//        property = new CR_swarm();
//        property = new CR_gridswarm();
//        property = new PR_swarm();
        property = new PR_gridswarm();
//        property = new AC_gridswarm();
//        property = new TCB_gridswarm();
//        property = new EP_token();
//        property = new EP_roundabout();
//        property = new EB_token();
//        property = new EB_roundabout();
//        property = new ER_token();
//        property = new ER_roundabout();

        Constraint constraint;
        constraint = new Racetrack();
//        constraint = new Gridwalk();

        Class node;
//        node = FloodNode.class;
        node = EliminationNode.class;

        MobilityModel mobilityModel = new MobilityModel(property, constraint, node);
        Topology topology = mobilityModel.getTopology();
        //new JViewer(topology);
        topology.start();

    }

}
