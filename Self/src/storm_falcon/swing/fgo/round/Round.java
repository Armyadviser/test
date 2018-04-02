package storm_falcon.swing.fgo.round;

import net.sf.json.JSONArray;
import storm_falcon.IniOperation;
import storm_falcon.swing.fgo.Button;
import storm_falcon.swing.fgo.ButtonManager;
import storm_falcon.swing.fgo.round.action.Action;
import storm_falcon.swing.fgo.round.action.ButtonAction;
import storm_falcon.swing.fgo.round.action.SleepAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Round {

    List<Action> actions = new ArrayList<>();

    public static Round parse(IniOperation ini, int roundId) {
        String roundType = ini.getKeyValue("rounds", "round" + roundId);
        if (roundType == null) {
            return null;
        }

        if (roundType.equals("random")) {
            return new RandomRound();
        }

        if (roundType.equals("intelligent")) {
            return new IntelligentRound();
        }

        String roundName = roundType;
        Round round = new ProgrammedRound();
        for (int i = 1; ; i++) {
            String sAction = ini.getKeyValue(roundName, "action" + i);
            if (sAction == null) {
                break;
            }

            Button button = ButtonManager.getButton(sAction);
            if (button != null) {
                round.actions.add(new ButtonAction(button));
            }
        }
        return round;
    }

    public void proceed() {
        actions.stream().peek(System.out::print).forEach(Action::proceed);
    }
}
