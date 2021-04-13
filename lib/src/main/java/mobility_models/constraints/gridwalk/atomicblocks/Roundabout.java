package mobility_models.constraints.gridwalk.atomicblocks;

import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import mobility_models.constraints.AtomicBlock;
import mobility_models.constraints.gridwalk.Gridwalk;

import java.util.ArrayList;
import java.util.List;

public class Roundabout extends AtomicBlock {

    @Override
    public void move(Node node) {
        _moveMaxSpeed(node);
    }

    private static void _moveMaxSpeed(Node node) {
        Gridwalk.updateMaxSpeed(node);
//        System.out.println("in movemaxspeed : max speed " + node.getProperty("max_speed") + " and _max_speed " + node.getProperty("_max_speed"));
        if (node.getProperty("_max_speed") == null || (int)node.getProperty("_max_speed") == 1) {
            _move(node);
        } else {
            int max_speed = (int) node.getProperty("_max_speed");
            if (node.getProperty("max_speed_timer") == null){
                node.setProperty("max_speed_timer", max_speed);
                _move(node);
            } else {
                int timer = (int) node.getProperty("max_speed_timer");
                timer--;
                if (timer <= 0){
                    timer = max_speed;
                    if (max_speed != 0)
                        _move(node);
                }
                node.setProperty("max_speed_timer", timer);
            }
        }
    }

    private static void _move(Node node) {
        if (node.getProperty("clockwise") != null){
            boolean clockwise = (boolean) node.getProperty("clockwise");
            if (clockwise){
                _moveClockwise(node);
            } else {
                _moveCounterClockwise(node);
            }
        } else
            _moveClockwise(node);
    }

    private static void _moveClockwise(Node node) {
        Point center = (Point) node.getProperty("center");
        double radius = (double) node.getProperty("radius");
        List<Point> validNeighbors = new ArrayList<>();

        for (Point neighbor : Gridwalk.getNeighbors(node)){
            double angle = getAngle(neighbor, center, node.getLocation());
            if (angle < 0){
                validNeighbors.add((Point) neighbor.clone());
            }
        }
        node.setLocation(closestNeighbor(validNeighbors, center, radius));
    }

    private static void _moveCounterClockwise(Node node) {
        Point center = (Point) node.getProperty("center");
        double radius = (double) node.getProperty("radius");
        List<Point> validNeighbors = new ArrayList<>();

        for (Point neighbor : Gridwalk.getNeighbors(node)){
            double angle = getAngle(neighbor, center, node.getLocation());
            if (angle > 0){
                validNeighbors.add((Point) neighbor.clone());
            }
        }
        node.setLocation(closestNeighbor(validNeighbors, center, radius));
    }

    private static Point closestNeighbor(List<Point> validNeighbors, Point center, double radius) {
        Point best_neighbor = validNeighbors.get(0);
        double best_distance = Math.abs(best_neighbor.distance(center) - radius);
        for (Point neighbor : validNeighbors){
            double distance = Math.abs(neighbor.distance(center) - radius);
            if (distance < best_distance){
                best_distance = distance;
                best_neighbor = neighbor;
            }
        }
        return best_neighbor;
    }

    private static double getAngle(Point P1, Point P2, Point P3) {
        double angle = Math.atan2(P3.y - P2.y, P3.x - P2.x) - Math.atan2(P1.y - P2.y, P1.x - P2.x); //angle magic
        if (angle > Math.PI)
            angle = angle - 2 * Math.PI;
        if (angle < -Math.PI)
            angle = angle + 2 * Math.PI;
        return angle;
    }
}
