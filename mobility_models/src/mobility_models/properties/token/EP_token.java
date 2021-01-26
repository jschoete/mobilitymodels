package mobility_models.properties.token;

import io.jbotsim.core.Link;
import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import io.jbotsim.core.event.ConnectivityListener;
import mobility_models.properties.Property;

public class EP_token extends Property implements ConnectivityListener {

    private Point center0;
    private double spacing;
    private double radius;
    private int rows;
    private int columns;
    protected Node tokenNode;

    @Override
    public void onStart() {
        super.onStart();
        this.setNodes(20);
        tp.addConnectivityListener(this);
        center0 = new Point(100, 100);
        spacing = 200;
        radius = 70;
        rows = 4;
        columns = 5;
        changeComrange(60);
        int i = 0;
        int j = 0;
        Point center;
        Point offset_left;
        Point offset_up;
        for (Node node : tp.getNodes()){
            center = new Point(center0.getX() + i * spacing, center0.getY() + j * spacing);
            node.setProperty("center", center);
            node.setProperty("radius", radius);
            offset_left = new Point(center.getX() - radius, center.getY());
            offset_up = new Point(center.getX(), center.getY() - radius);
            if (i == 0 && j == 0){
                tokenNode = node;
                node.setProperty("state", "roundabout");
            } else {
                node.setProperty("state", "messenger");
            }
            if (j == 0){
                node.setLocation(offset_left);
                node.setProperty("destination", offset_left);
            } else if (i == 0){
                node.setLocation(offset_up);
                node.setProperty("destination", offset_up);
            } else if (Math.random() < .5) {
                node.setLocation(offset_left);
                node.setProperty("destination", offset_left);
            } else {
                node.setLocation(offset_up);
                node.setProperty("destination", offset_up);
            }
            i++;
            if (i == columns){
                j++;
                i = 0;
            }
        }
    }

    private void changeComrange(int cr) {
        for (Node node: tp.getNodes()){
            node.setCommunicationRange(cr);
        }
    }

    @Override
    public void onLinkAdded(Link link) {
        Node newTokenNode = link.getOtherEndpoint(tokenNode);
        tokenNode.setProperty("state", "messenger");
        tokenNode.setProperty("destination", tokenNode.getLocation());
        tokenNode = newTokenNode;
        tokenNode.setProperty("state", "roundabout");
    }

    @Override
    public void onLinkRemoved(Link link) {}
}
