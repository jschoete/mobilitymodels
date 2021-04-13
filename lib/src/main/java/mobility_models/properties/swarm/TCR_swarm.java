package mobility_models.properties.swarm;

import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import mobility_models.properties.Property;

public class TCR_swarm extends Property {

    protected Point swarm1_center;
    protected Point swarm2_center;
    protected Integer swarm1_radius;
    protected Integer swarm2_radius;
    protected Node messenger;

    @Override
    public void onStart() {
        super.onStart();
        this.setNodes(40);
        swarm1_center = new Point(200, 400);
        swarm2_center = new Point(800, 400);
        swarm1_radius = 150;
        swarm2_radius = 150;
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

    @Override
    public void onClock() {
        Point d = (Point) messenger.getProperty("destination");
        if (messenger.getLocation().equals(d) && Math.random() < .01){
            if (d.equals(swarm1_center)){
                messenger.setProperty("destination", swarm2_center);
            } else {
                messenger.setProperty("destination", swarm1_center);
            }
        }
    }
}
