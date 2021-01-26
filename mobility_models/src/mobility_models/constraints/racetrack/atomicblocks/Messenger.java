package mobility_models.constraints.racetrack.atomicblocks;

import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import mobility_models.constraints.AtomicBlock;
import mobility_models.constraints.racetrack.Racetrack;

public class Messenger extends AtomicBlock {

    @Override
    public void move(Node node) {
        _move(node);
    }

    public static void _move(Node node) {
        if (node.getProperty("max_speed") != null)
            computeVector_maxSpeed(node);
        else
            computeVector_noMaxSpeed(node);
        int dx = (int) node.getProperty("dx");
        int dy = (int) node.getProperty("dy");
        node.setLocation(node.getX() + dx, node.getY() + dy);
    }

    private static void computeVector_noMaxSpeed(Node node) {
        Point dest = (Point) node.getProperty("destination");
        int dest_x = (int) dest.getX();
        int x = (int) node.getX();
        int dx = (int) node.getProperty("dx");
        if (dest_x > x) {
            node.setProperty("dx", dx + normalized_modif(dest_x - x, dx));
        } else {
            node.setProperty("dx", dx - normalized_modif(x - dest_x, -dx));
        }
        int dest_y = (int) dest.getY();
        int y = (int) node.getY();
        int dy = (int) node.getProperty("dy");
        if (dest_y > y) {
            node.setProperty("dy", dy + normalized_modif(dest_y - y, dy));
        } else {
            node.setProperty("dy", dy - normalized_modif(y - dest_y, -dy));
        }
    }

    private static void computeVector_maxSpeed(Node node) {
        int max_speed = (int) node.getProperty("max_speed");
        Point dest = (Point) node.getProperty("destination");
        int dest_x = (int) dest.getX();
        int x = (int) node.getX();
        int dx = (int) node.getProperty("dx");
        if (dx > max_speed){
            node.setProperty("dx", dx - 1);
        } else if (dx < -max_speed){
            node.setProperty("dx", dx + 1);
        } else {
            int newdx;
            if (dest_x > x) {
                newdx = dx + normalized_modif(dest_x - x, dx);
            } else {
                newdx = dx - normalized_modif(x - dest_x, -dx);
            }
            if (newdx > max_speed){
                node.setProperty("dx", max_speed);
            } else if (newdx < -max_speed){
                node.setProperty("dx", -max_speed);
            } else {
                node.setProperty("dx", newdx);
            }
        }
        int dest_y = (int) dest.getY();
        int y = (int) node.getY();
        int dy = (int) node.getProperty("dy");
        if (dy > max_speed){
            node.setProperty("dy", dy - 1);
        } else if (dy < -max_speed){
            node.setProperty("dy", dy + 1);
        } else {
            int newdy;
            if (dest_y > y) {
                newdy = dy + normalized_modif(dest_y - y, dy);
            } else {
                newdy = dy - normalized_modif(y - dest_y, -dy);
            }
            if (newdy > max_speed){
                node.setProperty("dy", max_speed);
            } else if (newdy < -max_speed){
                node.setProperty("dy", -max_speed);
            } else {
                node.setProperty("dy", newdy);
            }
        }
    }

    private static int normalized_modif(int dist, int dx) {
        if (dx <= 0 && dist != 0)
            return 1;
        int br_dist;
        if (dx < 0){
            br_dist = Racetrack.braking_distance(-dx);
        } else {
            br_dist = Racetrack.braking_distance(dx);
        }
        int diff = dist - br_dist;
        if (diff < dx) {
            return -1;
        }
        if (diff > dx) {
            if (!bypasses(dx + 1, dist)) {
                return 1;
            }
        }
        return 0;
    }

    private static boolean bypasses(int dx, int dist) {
        return dx + Racetrack.braking_distance(dx) > dist;
    }
}
