package mobility_models.constraints.racetrack;

import io.jbotsim.core.Node;
import mobility_models.constraints.Constraint;
import mobility_models.constraints.racetrack.atomicblocks.Messenger;
import mobility_models.constraints.racetrack.atomicblocks.Roundabout;
import mobility_models.constraints.racetrack.atomicblocks.Swarm;

public class Racetrack extends Constraint {

    //TODO implement below consensus strategy for ensuring EB_roundabout
//    private static int consensus = 0; //needed in case of max speeds

    @Override
    public void onStart() {
        super.onStart();
        for (Node n : tp.getNodes()) {
            n.setProperty("dx", 0);
            n.setProperty("dy", 0);
        }
        messenger = new Messenger();
        roundabout = new Roundabout();
        swarm = new Swarm();
        tp.setTimeUnit(tp.getTimeUnit()*4);
    }

    @Override
    public void onClock() {
        super.onClock();
//        if (consensus == tp.getTime()){
////            System.out.println("consensus at time " + tp.getTime());
//            if (tp.getNodes().get(0).getProperty("max_speed") != null){
//                Set<Integer> max_speeds = new HashSet<>();
//                for (Node node : tp.getNodes()){
//                    if (node.getProperty("max_speed") != null)
//                        max_speeds.add((Integer) node.getProperty("max_speed"));
//                }
//                int period = lcm(max_speeds);
//                consensus = tp.getTime() + period;
//                for (Node node : tp.getNodes()){
//                    node.setProperty("consensus", consensus);
//                }
////                System.out.println("consensus set to " + consensus);
////                System.out.println("for speeds " + max_speeds.toString());
//            }
//        }
    }

    public static int braking_distance(int dx) {
        return dx * (dx - 1) / 2;
    }

    // minimum amount of vectors needed to close distance d, from zero speed to zero speed
    // closed form from integer sequence A027434 on OEIS
    public static int vectors(int d){
        return (int) Math.ceil(2 * Math.sqrt(d));
    }
}
