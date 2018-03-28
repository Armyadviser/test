package storm_falcon.swing.fgo.round;

import net.sf.json.JSONArray;
import storm_falcon.swing.fgo.ButtonManager;
import storm_falcon.swing.fgo.round.Round;
import storm_falcon.swing.fgo.round.action.Action;
import storm_falcon.swing.fgo.round.action.ButtonAction;

import java.util.Random;

public class RandomRound extends Round {

    private final static Random random = new Random(23);

    public RandomRound(JSONArray array) {
        super(array);
    }

    public void init() {
        Action action = new ButtonAction(ButtonManager.getButton("attack"));
    }
}
