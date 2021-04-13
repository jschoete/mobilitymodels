package mobility_models.constraints.gridwalk;

import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import mobility_models.constraints.Constraint;
import mobility_models.constraints.gridwalk.atomicblocks.Messenger;
import mobility_models.constraints.gridwalk.atomicblocks.Roundabout;
import mobility_models.constraints.gridwalk.atomicblocks.Swarm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Gridwalk extends Constraint {

//    private static boolean flagConsensus = true;
//    private static int currentTimestep = 1;
    private static int consensus = 0; //needed in case of max speeds

    @Override
    public void onStart() {
        super.onStart();
        messenger = new Messenger();
        roundabout = new Roundabout();
        swarm = new Swarm();
        tp.setTimeUnit(tp.getTimeUnit()/5);
        for (Node node : tp.getNodes())
            node.setProperty("consensus", 0);
//        System.out.println(tp.getNodes().size());
//        System.out.println(tp.getTime());
    }

    @Override
    public void onClock() {
        super.onClock();
        if (consensus == tp.getTime()){
//            System.out.println("consensus at time " + tp.getTime());
            if (tp.getNodes().get(0).getProperty("max_speed") != null){
                Set<Integer> max_speeds = new HashSet<>();
                for (Node node : tp.getNodes()){
                    if (node.getProperty("max_speed") != null)
                        max_speeds.add((Integer) node.getProperty("max_speed"));
                }
                int period = lcm(max_speeds);
                consensus = tp.getTime() + period;
                for (Node node : tp.getNodes()){
                    node.setProperty("consensus", consensus);
                }
//                System.out.println("consensus set to " + consensus);
//                System.out.println("for speeds " + max_speeds.toString());
            }
        }
    }

    public static void updateMaxSpeed(Node node) {
        int consensus = (int) node.getProperty("consensus");
        if (node.getProperty("max_speed") != null && node.getTopology().getTime() == consensus){
            int max_speed = (int) node.getProperty("max_speed");
            node.setProperty("_max_speed", max_speed);
        }
    }

    public static List<Point> getNeighbors(Node node){
        List<Point> neighbors = new ArrayList<>();
        Point location = node.getLocation();
        neighbors.add(new Point(location.getX() + 1, location.getY()));
        neighbors.add(new Point(location.getX() - 1, location.getY()));
        neighbors.add(new Point(location.getX(), location.getY() + 1));
        neighbors.add(new Point(location.getX(), location.getY() - 1));
        return neighbors;
    }
}
