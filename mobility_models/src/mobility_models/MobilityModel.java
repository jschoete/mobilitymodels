package mobility_models;

import io.jbotsim.core.Topology;
import io.jbotsim.ui.JViewer;
import mobility_models.constraints.Constraint;
import mobility_models.properties.Property;

public class MobilityModel {

    private Topology tp;

    public MobilityModel(Property p, Constraint c){
        tp = new Topology(1000, 800);
        tp.setRefreshMode(Topology.RefreshMode.CLOCKBASED); // to avoid multiple onLinkAdded() during onClock()
//        for (int i=0; i<20; i++)
//            tp.addNode(0,0);
        p.setTopology(tp);
        c.setTopology(tp);
        tp.addStartListener(p);
        tp.addStartListener(c);
        new JViewer(tp);
        tp.start();
        tp.pause();
    }

    public MobilityModel(Property p, Constraint c, Class nodeclass){
        tp = new Topology(1000, 800);
        tp.setRefreshMode(Topology.RefreshMode.CLOCKBASED); // to avoid multiple onLinkAdded() during onClock()
        tp.setDefaultNodeModel(nodeclass);
//        for (int i=0; i<20; i++)
//            tp.addNode(0,0);
        p.setTopology(tp);
        c.setTopology(tp);
        tp.addStartListener(p);
        tp.addStartListener(c);
        new JViewer(tp);
        tp.start();
        tp.pause();
    }

    public MobilityModel(Property p, Constraint c, boolean viewer){
        tp = new Topology(1000, 800);
        tp.setRefreshMode(Topology.RefreshMode.CLOCKBASED); // to avoid multiple onLinkAdded() during onClock()
//        for (int i=0; i<20; i++)
//            tp.addNode(0,0);
        p.setTopology(tp);
        c.setTopology(tp);
        tp.addStartListener(c);
        tp.addStartListener(p);
        if (viewer)
            new JViewer(tp);
        tp.start();
        tp.pause();
    }

    public Topology getTopology(){
        return tp;
    }
}
