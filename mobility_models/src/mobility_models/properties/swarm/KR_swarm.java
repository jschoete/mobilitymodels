package mobility_models.properties.swarm;

import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import mobility_models.properties.Property;

import java.util.Random;

public class KR_swarm extends Property {

    protected Point swarm_center;
    protected int swarm_radius;

    @Override
    public void onStart() {
        super.onStart();
        this.setNodes(40);
        swarm_center = new Point(500, 400);
        swarm_radius = 300;
        for (Node node : tp.getNodes()) {
            node.setLocation(randomLocation(swarm_center, swarm_radius));
            node.setProperty("state", "swarm");
            node.setProperty("center", swarm_center);
            node.setProperty("radius", swarm_radius);
        }
    }
}
