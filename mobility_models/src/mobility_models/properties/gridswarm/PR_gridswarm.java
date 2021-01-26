package mobility_models.properties.gridswarm;

import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import mobility_models.properties.swarm.PR_swarm;

public class PR_gridswarm extends PR_swarm {

    private int swarm_counter;

    @Override
    public void onStart() {
        super.onStart();
        swarm_counter = 0;
        makeGrid(swarm1_center, swarm1_radius);
        swarm_counter++;
        makeGrid(swarm2_center, swarm2_radius);
        swarm_counter++;
        makeGrid(swarm3_center, swarm3_radius);
    }

    private void makeGrid(Point swarm_center, Integer swarm_radius) {
        Point topleft = new Point(swarm_center.getX() - swarm_radius, swarm_center.getY() - swarm_radius);
        int nb_columns = (int) (2 * swarm_radius / comrange + 1);
        int nb_rows = nb_columns;
        Point p;
        int i = swarm_counter * (n / 3) + 1;
        Node gridAgent;

        for (int x = 0; x < nb_columns; x++) {
            for (int y = 0; y < nb_rows; y++) {
                p = new Point(topleft.getX() + x * comrange, topleft.getY() + y * comrange);
                if (p.distance(swarm_center) <= swarm_radius) {
                    gridAgent = tp.getNodes().get(i);
                    gridAgent.setLocation(p);
                    gridAgent.setProperty("state", "messenger");
                    gridAgent.setProperty("destination", p);
                    i++;
                }
            }
        }
    }
}
