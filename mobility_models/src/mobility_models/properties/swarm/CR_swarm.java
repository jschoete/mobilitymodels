package mobility_models.properties.swarm;

import io.jbotsim.core.Node;
import io.jbotsim.core.Point;

public class CR_swarm extends TCR_swarm {

    @Override
    public void onStart() {
        super.onStart();
        swarm1_center = new Point(275, 400);
        swarm2_center = new Point(725, 400);
//        swarm2_radius = 200;
//        swarm1_radius = 200;
        for (Node node : tp.getNodes()) {
            if (node.getID() < n / 2) {
                node.setLocation(randomLocation(swarm1_center, swarm1_radius));
                node.setProperty("state", "swarm");
                node.setProperty("center", swarm1_center);
                node.setProperty("radius", swarm1_radius);
            } else {
                node.setLocation(randomLocation(swarm2_center, swarm2_radius));
                node.setProperty("state", "swarm");
                node.setProperty("center", swarm2_center);
                node.setProperty("radius", swarm2_radius);
            }

        }
        messenger = tp.getNodes().get(0);
        messenger.setProperty("state", "messenger");
        messenger.setProperty("destination", swarm1_center);
    }
}
