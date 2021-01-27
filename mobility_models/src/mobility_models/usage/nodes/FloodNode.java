package mobility_models.usage.nodes;

import io.jbotsim.core.Color;
import io.jbotsim.core.Message;
import io.jbotsim.core.Node;

public class FloodNode extends Node {

    @Override
    public void onStart() {
        super.onStart();
        if (this.getID() == 0)
            this.setColor(Color.GREEN);
    }

    @Override
    public void onClock() {
        if (this.getColor() != null)
            this.sendAll(new Message());
    }

    @Override
    public void onMessage(Message message) {
        super.onMessage(message);
        this.setColor(Color.GREEN);
    }
}
