package mobility_models;

import io.jbotsim.core.Topology;
import mobility_models.constraints.Constraint;
import mobility_models.properties.Property;

public class MobilityModel {

    private Topology tp;

    public MobilityModel(Property p, Constraint c){
        initialize(p, c, new Topology(1000, 800));
    }

    private void initialize(Property p, Constraint c, Topology tp) {
        this.tp = tp;
        this.tp.setRefreshMode(Topology.RefreshMode.CLOCKBASED); // to avoid multiple onLinkAdded() during onClock()
//        for (int i=0; i<20; i++)
//            tp.addNode(0,0);
        p.setTopology(this.tp);
        c.setTopology(this.tp);
        this.tp.addStartListener(p);
        this.tp.addStartListener(c);
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
    }

    public MobilityModel(Property p, Constraint c, Topology topology){
        initialize(p, c, topology);
    }

    public Topology getTopology(){
        return tp;
    }
    public void start() { getTopology().start();}
}
