package mobility_models.constraints;

import io.jbotsim.core.Node;
import io.jbotsim.core.Topology;
import io.jbotsim.core.event.ClockListener;
import io.jbotsim.core.event.StartListener;

import java.util.Set;

public abstract class Constraint implements ClockListener, StartListener {

    protected Topology tp;
    protected AtomicBlock messenger;
    protected AtomicBlock roundabout;
    protected AtomicBlock swarm;

    public void setTopology(Topology tp) {
        this.tp = tp;
    }

    public void onStart() {
        this.tp.addClockListener(this);
    }

    @Override
    public void onClock() {
        for (Node n : this.tp.getNodes()) {
            move(n);
        }
    }

    public void move(Node node) {
        switch ((String) node.getProperty("state")) {
            case "messenger":
                messenger.move(node);
                break;
            case "roundabout":
                roundabout.move(node);
                break;
            case "swarm":
                swarm.move(node);
                break;
        }
    }

    private static int gcd(int a, int b){
        while (b > 0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static int lcm(int a, int b){
        return a * (b / gcd(a, b));
    }

    protected static int lcm(Set<Integer> input){
        int result = 0;
        boolean flag = true;
        for (int element : input){
            if (flag){
                flag = false;
                result = element;
            } else {
                result = lcm(result, element);
            }
        }
        if (result == 0)
            return 1;
        return result;
    }
}
