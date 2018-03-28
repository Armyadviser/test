package storm_falcon.swing.fgo.round;

import net.sf.json.JSONArray;
import storm_falcon.swing.fgo.ButtonManager;
import storm_falcon.swing.fgo.round.action.Action;
import storm_falcon.swing.fgo.round.action.ButtonAction;
import storm_falcon.swing.fgo.round.action.SleepAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Round {

    private List<Action> actions;

    public Round(JSONArray array) {
        actions = Arrays.stream(array.toArray())
                .map(String::valueOf)
                .map(text -> {
                    if (text.startsWith("sleep")) {
                        long n = Long.valueOf(text.substring(5, text.length()));
                        return new SleepAction(n);
                    }
                    return new ButtonAction(ButtonManager.getButton(text));
                }).collect(Collectors.toList());
    }

    public void proceed() {
        actions.stream().peek(System.out::print).forEach(Action::proceed);
    }
}
