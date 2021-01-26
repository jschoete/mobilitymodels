package mobility_models.properties.roundabout;

import io.jbotsim.core.Link;
import io.jbotsim.core.Node;

import java.util.Random;

public class EB_roundabout extends EP_roundabout {

    @Override
    public void onStart() {
        super.onStart();
        for (Node node : tp.getNodes()){
            node.setProperty("original_max_speed", node.getProperty("max_speed"));
        }
    }

    @Override
    public void onClock() {
        super.onClock();
        if (Math.random() < .01){
            int factor = new Random().nextInt(3) + 1;
            for (Node node : tp.getNodes()){
                node.setProperty("max_speed", factor * (int)node.getProperty("original_max_speed"));
//                System.out.println("max speed of node " + node.getID() + " set to " + node.getProperty("max_speed"));
//                System.out.println("original max speed of node " + node.getID() + " : " + node.getProperty("original_max_speed"));
            }
        }
    }

    @Override
    public void onLinkAdded(Link link) {
        super.onLinkAdded(link);
        Node n0 = link.endpoint(0);
        Node n1 = link.endpoint(1);
        if (n0.getProperty("original_max_speed") == null)
            n0.setProperty("original_max_speed", n0.getProperty("max_speed"));
        if (n1.getProperty("original_max_speed") == null)
            n1.setProperty("original_max_speed", n1.getProperty("max_speed"));
    }
}
