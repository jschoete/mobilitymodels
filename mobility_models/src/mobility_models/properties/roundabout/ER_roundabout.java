package mobility_models.properties.roundabout;

import io.jbotsim.core.Node;

public class ER_roundabout extends EB_roundabout {

    @Override
    public void onClock() {
        super.onClock();
        if (Math.random() < .003){
            for (Node node : tp.getNodes()){
                node.setProperty("max_speed", 0);
            }
        }
    }
}
