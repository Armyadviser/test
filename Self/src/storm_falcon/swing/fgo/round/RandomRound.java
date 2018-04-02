package storm_falcon.swing.fgo.round;

import net.sf.json.JSONArray;
import storm_falcon.swing.fgo.ButtonManager;
import storm_falcon.swing.fgo.round.Round;
import storm_falcon.swing.fgo.round.action.Action;
import storm_falcon.swing.fgo.round.action.ButtonAction;

import java.util.Arrays;
import java.util.Random;

public class RandomRound extends Round {

    private final static Random random = new Random(23);

    RandomRound() {
        init();
    }

    private void init() {
        actions = Arrays.asList(new ButtonAction(ButtonManager.getButton("attack")),
                new ButtonAction(ButtonManager.getButton("card" + random.nextInt(5))),
                new ButtonAction(ButtonManager.getButton("card" + random.nextInt(5))),
                new ButtonAction(ButtonManager.getButton("card" + random.nextInt(5))));
    }
}
