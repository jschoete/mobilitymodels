package mobility_models.constraints.gridwalk.atomicblocks;

import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import mobility_models.constraints.AtomicBlock;
import mobility_models.constraints.gridwalk.Gridwalk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Swarm extends AtomicBlock {

    @Override
    public void move(Node node) {
        _move(node);
    }

    private static void _move(Node node) {
        List<Point> options = new ArrayList();
        for (Point neighbor : Gridwalk.getNeighbors(node)){
            if (isValid(neighbor, node)){
                options.add(neighbor);
            }
        }
        Point next = options.get(new Random().nextInt(options.size()));
        node.setLocation(next);
    }

    private static boolean isValid(Point neighbor, Node node) {
        Point center = (Point) node.getProperty("center");
        int radius = (int) node.getProperty("radius");
        return neighbor.distance(center) <= radius;
    }
}
