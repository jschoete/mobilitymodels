package mobility_models.properties.token;

import io.jbotsim.core.Link;

import java.util.Random;

public class EB_token extends EP_token {

    private int max_speed;
    private int min_speed;
    protected int current_speed;

    @Override
    public void onStart() {
        super.onStart();
        max_speed = 5;
        min_speed = 1;
        current_speed = new Random().nextInt(max_speed) + min_speed;
        tokenNode.setProperty("max_speed", current_speed);
    }

    @Override
    public void onClock() {
        super.onClock();
        if (Math.random() < .01){
            current_speed = new Random().nextInt(max_speed) + min_speed;
            tokenNode.setProperty("max_speed", current_speed);
        }
    }

    @Override
    public void onLinkAdded(Link link) {
        super.onLinkAdded(link);
        tokenNode.setProperty("max_speed", current_speed);
    }
}
