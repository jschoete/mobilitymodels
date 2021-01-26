package mobility_models.constraints.racetrack.atomicblocks;

import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import mobility_models.constraints.AtomicBlock;

public class Roundabout extends AtomicBlock {

    //TODO check if the "pick closest option to radius" works, and if so implement it

    @Override
    public void move(Node node) {
        _move(node);
    }

    private static void _move(Node node) {
        if (node.getLocation().equals(node.getProperty("destination"))
                && (int)node.getProperty("dx") == 0 && (int)node.getProperty("dy") == 0) {
            changeTarget(node);
        }
        Messenger._move(node);
    }

    private static void changeTarget(Node node) {
        if (node.getProperty("clockwise") != null){
            boolean clockwise = (boolean) node.getProperty("clockwise");
            if (clockwise){
                changeTarget_clockwise(node);
            } else {
                changeTarget_counterClockwise(node);
            }
        } else
            changeTarget_clockwise(node);
    }

    private static void changeTarget_clockwise(Node node) {
        Point dest = (Point) node.getProperty("destination");
        Point center = (Point) node.getProperty("center");
        double radius = (double) node.getProperty("radius");
        Point new_dest;
        if (dest.getY() < center.getY())
            new_dest = new Point(center.getX() + radius, center.getY());
        else if (dest.getY() > center.getY())
            new_dest = new Point(center.getX() - radius, center.getY());
        else if (dest.getX() < center.getX())
            new_dest = new Point(center.getX(), center.getY() - radius);
        else //if (target.getX() > center.getX())
            new_dest = new Point(center.getX(), center.getY() + radius);
        node.setProperty("destination", new_dest);
    }

    private static void changeTarget_counterClockwise(Node node) {
        Point dest = (Point) node.getProperty("destination");
        Point center = (Point) node.getProperty("center");
        double radius = (double) node.getProperty("radius");
        Point new_dest;
        if (dest.getY() < center.getY())
            new_dest = new Point(center.getX() - radius, center.getY());
        else if (dest.getY() > center.getY())
            new_dest = new Point(center.getX() + radius, center.getY());
        else if (dest.getX() < center.getX())
            new_dest = new Point(center.getX(), center.getY() + radius);
        else //if (target.getX() > center.getX())
            new_dest = new Point(center.getX(), center.getY() - radius);
        node.setProperty("destination", new_dest);
    }
}
