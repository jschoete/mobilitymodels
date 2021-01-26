package mobility_models.properties.token;

public class ER_token extends EB_token {

    @Override
    public void onClock() {
        super.onClock();
        if (Math.random() < .003){
            current_speed = 0;
            tokenNode.setProperty("max_speed", current_speed);
        }
    }
}
