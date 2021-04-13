package mobility_models.properties.swarm;

import io.jbotsim.core.Node;
import io.jbotsim.core.Point;

public class PR_swarm extends CR_swarm {

    protected Point swarm3_center;
    protected Integer swarm3_radius;

    @Override
    public void onStart() {
        super.onStart();
        swarm1_center = new Point(275, 570);
        swarm2_center = new Point(725, 570);
        swarm3_center = new Point(500, 180);
        swarm3_radius = 150;
        for (Node node : tp.getNodes()) {
            if (node.getID() < n / 3) {
                node.setLocation(randomLocation(swarm1_center, swarm1_radius));
                node.setProperty("state", "swarm");
                node.setProperty("center", swarm1_center);
                node.setProperty("radius", swarm1_radius);
            } else if (node.getID() < 2 * n / 3) {
                node.setLocation(randomLocation(swarm2_center, swarm2_radius));
                node.setProperty("state", "swarm");
                node.setProperty("center", swarm2_center);
                node.setProperty("radius", swarm2_radius);
            } else {
                node.setLocation(randomLocation(swarm3_center, swarm3_radius));
                node.setProperty("state", "swarm");
                node.setProperty("center", swarm3_center);
                node.setProperty("radius", swarm3_radius);
            }
        }
        messenger = tp.getNodes().get(0);
        messenger.setProperty("state", "messenger");
        messenger.setProperty("destination", swarm1_center);
    }

    @Override
    public void onClock() {
        Point d = (Point) messenger.getProperty("destination");
        if (messenger.getLocation().equals(d)) {
            if (d.equals(swarm1_center)) {
                if (Math.random() < .5)
                    messenger.setProperty("destination", swarm2_center);
                else
                    messenger.setProperty("destination", swarm3_center);
            } else if (d.equals(swarm2_center)) {
                if (Math.random() < .5)
                    messenger.setProperty("destination", swarm1_center);
                else
                    messenger.setProperty("destination", swarm3_center);
            } else {
                if (Math.random() < .5)
                    messenger.setProperty("destination", swarm1_center);
                else
                    messenger.setProperty("destination", swarm2_center);
            }
        }
    }
}
