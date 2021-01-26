package mobility_models.usage.distributed.nodes;

import io.jbotsim.core.Color;
import io.jbotsim.core.Link;
import io.jbotsim.core.Node;

public class EliminationNode extends Node {
    @Override
    public void onStart() {
        super.onStart();
        this.setColor(Color.GREEN);
    }

    @Override
    public void onLinkAdded(Link link) {
        super.onLinkAdded(link);
        Node n0 = link.endpoint(0);
        Node n1 = link.endpoint(1);
        if (n0.getColor().equals(Color.GREEN))
            n1.setColor(Color.RED); //elimination
    }
}
