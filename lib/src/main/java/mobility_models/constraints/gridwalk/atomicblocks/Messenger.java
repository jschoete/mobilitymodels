package mobility_models.constraints.gridwalk.atomicblocks;

import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import mobility_models.constraints.AtomicBlock;
import mobility_models.constraints.gridwalk.Gridwalk;

public class Messenger extends AtomicBlock {

    @Override
    public void move(Node node) {
        _moveMaxSpeed(node);
    }

    public static void _moveMaxSpeed(Node node){
        Gridwalk.updateMaxSpeed(node);
        if (node.getProperty("_max_speed") == null || (int)node.getProperty("_max_speed") == 1) {
            _move(node);
        } else {
            int max_speed = (int) node.getProperty("_max_speed");
            if (node.getProperty("max_speed_timer") == null){
                node.setProperty("max_speed_timer", max_speed);
            } else {
                int timer = (int) node.getProperty("max_speed_timer");
                timer--;
                if (timer == 0){
                    timer = max_speed;
                    _move(node);
                }
                node.setProperty("max_speed_timer", timer);
            }
        }
    }

    public static void _move(Node node){
        Point location = node.getLocation();
        Point destination = (Point) node.getProperty("destination");
        double best_distance = location.distance(destination);
        Point next = location;
        for (Point neighbor : Gridwalk.getNeighbors(node)) {
            double distance = neighbor.distance(destination);
            if (distance < best_distance) {
                best_distance = distance;
                next = neighbor;
            }
        }
        node.setLocation(next);
    }

    // testing

//    public static void main(String[] args) {
//        Topology tp = new Topology();
//        Constraint c = new Gridwalk();
//        c.setTopology(tp);
//        tp.addStartListener(c);
//        tp.addNode(10, 10);
//        Node n0 = tp.getNodes().get(0);
//        n0.setProperty("state", "messenger");
//        n0.setProperty("destination", new Point(500, 220));
//        JViewer jv = new JViewer(tp);
//    }
}
