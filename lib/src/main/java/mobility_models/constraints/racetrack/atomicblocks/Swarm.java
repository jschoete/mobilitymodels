package mobility_models.constraints.racetrack.atomicblocks;

import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import mobility_models.constraints.AtomicBlock;
import mobility_models.constraints.racetrack.Racetrack;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Swarm extends AtomicBlock {

    //TODO isValidVector() returns false for potentially valid vectors (see swarm_racetrack_computation.pdf)

    @Override
    public void move(Node node) {
        _move(node);
    }

    private static void _move(Node node) {
        int dx = (int) node.getProperty("dx");
        int dy = (int) node.getProperty("dy");
        List<Point> validVectors = new LinkedList();
        for (int i=-1; i<=1; i++){
            for (int j=-1; j<=1; j++){
                int dxx = dx + i;
                int dyy = dy + j;
                if (isValidVector(node, dxx, dyy)){
                    validVectors.add(new Point(dxx, dyy));
                }
            }
        }
        try {
            Point dxy = validVectors.get(new Random().nextInt(validVectors.size()));
            dx = (int) dxy.getX();
            dy = (int) dxy.getY();
            node.setProperty("dx", dx);
            node.setProperty("dy", dy);
            node.setLocation(node.getX() + dx, node.getY() + dy);
        } catch (Exception exception) {
            System.out.println(node.getLocation());
            System.out.println(node.distance((Point) node.getProperty("center")));
        }
    }

    private static boolean isValidVector(Node node, int dx, int dy) {
        Point center = (Point) node.getProperty("center");
        int radius = (int) node.getProperty("radius");
        int x = (int) node.getX() + dx;
        int y = (int) node.getY() + dy;

        // testing if inside swarm, through braking
        int x_stop = x + (dx < 0 ? -1 : 1) * Racetrack.braking_distance(Math.abs(dx));
        int y_stop = y + (dy < 0 ? -1 : 1) * Racetrack.braking_distance(Math.abs(dy));
        Point stop = new Point(x_stop, y_stop);
        if (stop.distance(center) <= radius)
            return true;
        return false;

        // hard test corresponding to idea in swarm_racetrack_computation.pdf
//        if (stop.getX() >= center.getX() && stop.getY() <= center.getY()){ //upper right corner
//            int abs_dx = Math.abs(dx);
//            int abs_dy = Math.abs(dy);
//            if (abs_dx <= abs_dy){
//                Point inside = new Point(center.getX() - radius, y_stop);
//                while(inside.distance(center) > radius){ // peut être amélioré en temps constant maybe
//                    inside.setLocation(inside.getX() + 1, inside.getY());
//                }
//
//            }
//        }
//        return true;
    }
}
