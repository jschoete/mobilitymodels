package mobility_models.properties.gridswarm;

import io.jbotsim.core.Link;
import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import io.jbotsim.core.event.ConnectivityListener;
import mobility_models.properties.swarm.KR_swarm;

public class AC_gridswarm extends KR_swarm implements ConnectivityListener {

    private boolean replacing;
    private Node grid_replacing;
    private Node swarm_replacing;

    @Override
    public void onStart() {
        super.onStart();
        tp.addConnectivityListener(this);
        makeGrid();
        replacing = false;
    }

    private void makeGrid() { //traverse the bounding box and assign node to grid if inside swarm
        Point topleft = new Point(swarm_center.getX() - swarm_radius, swarm_center.getY() - swarm_radius);
        int nb_columns = (int) (2 * swarm_radius / comrange + 1);
        int nb_rows = nb_columns;
        Point p;
        int i = 0;
        Node gridAgent;

        for (int x=0; x<nb_columns; x++){
            for (int y=0; y<nb_rows; y++){
                p = new Point(topleft.getX() + x * comrange, topleft.getY() + y * comrange);
                if (p.distance(swarm_center) <= swarm_radius){
                    gridAgent = tp.getNodes().get(i);
                    gridAgent.setLocation(p);
                    gridAgent.setProperty("state", "messenger");
                    gridAgent.setProperty("destination", p);
                    i++;
                }
            }
        }
    }

    @Override
    public void onClock() {
        super.onClock();
        if (replacing){
            Point destination = grid_replacing.getLocation();
            if (swarm_replacing.getLocation().equals(destination)){
                replacing = false;
                grid_replacing.setProperty("state", "swarm");
            }
        }
    }

    @Override
    public void onLinkAdded(Link link) {
        Node n0 = link.endpoint(0);
        Node n1 = link.endpoint(1);
        if (!replacing
                && (n0.getProperty("state").equals("swarm") && n1.getProperty("state").equals("messenger")
                || n1.getProperty("state").equals("swarm") && n0.getProperty("state").equals("messenger"))
                && Math.random() < .1){
            replacing = true;
            if (n0.getProperty("state").equals("swarm") && n1.getProperty("state").equals("messenger")){
                grid_replacing = n1;
                swarm_replacing = n0;
            } else if (n1.getProperty("state").equals("swarm") && n0.getProperty("state").equals("messenger")){
                grid_replacing = n0;
                swarm_replacing = n1;
            }
            swarm_replacing.setProperty("state", "messenger");
            swarm_replacing.setProperty("destination", grid_replacing.getLocation());
        }
    }

    @Override
    public void onLinkRemoved(Link link) {

    }
}
