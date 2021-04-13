package mobility_models.properties;

import io.jbotsim.core.Point;
import io.jbotsim.core.Topology;
import io.jbotsim.core.event.ClockListener;
import io.jbotsim.core.event.StartListener;

import java.util.Random;

public abstract class Property implements StartListener, ClockListener {

    protected Topology tp;
    protected double comrange;
    protected int n;

    public void setTopology(Topology tp){
        this.tp = tp;
    }

    @Override
    public void onStart() {
        this.tp.addClockListener(this);
//        n = tp.getNodes().size();
    }

    @Override
    public void onClock() {}

    public void setNodes(int n){
        if (!tp.getNodes().isEmpty()) {
            System.out.println("Topology already has nodes, cannot introduce new ones.");
            return;
        }
        for (int i=0; i<n; i++){
            tp.addNode(0, 0);
        }
        this.n = n;
        comrange = tp.getNodes().get(0).getCommunicationRange();
    }

    protected Point randomLocation(Point swarm_center, int swarm_radius) {
        double angle = new Random().nextDouble() * 2 * Math.PI;
        double x = Math.cos(angle);
        double y = Math.sin(angle);
        x = x * Math.random() * swarm_radius;
        y = y * Math.random() * swarm_radius;
        x = x < 0 ? Math.ceil(x) : Math.floor(x);
        y = y < 0 ? Math.ceil(y) : Math.floor(y);
        x = x + swarm_center.getX();
        y = y + swarm_center.getY();
        return new Point(x, y);
    }
}
