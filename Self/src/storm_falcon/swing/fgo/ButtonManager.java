package storm_falcon.swing.fgo;

import java.util.HashMap;
import java.util.Map;

public class ButtonManager {

    private static final Button START = new Button("START", 1780 / Screen.BASE_WIDTH, 1020 / Screen.BASE_HEIGHT);
    private static final Button NEXT = new Button("NEXT", 1660 / Screen.BASE_WIDTH, 1020 / Screen.BASE_HEIGHT);
    private static final Button[] CARDS = new Button[] {
            new Button("CARD0", 200 / Screen.BASE_WIDTH, 750 / Screen.BASE_HEIGHT),
            new Button("CARD1", 580 / Screen.BASE_WIDTH, 750 / Screen.BASE_HEIGHT),
            new Button("CARD2", 930 / Screen.BASE_WIDTH, 750 / Screen.BASE_HEIGHT),
            new Button("CARD3", 1340 / Screen.BASE_WIDTH, 750 / Screen.BASE_HEIGHT),
            new Button("CARD4", 1750 / Screen.BASE_WIDTH, 750 / Screen.BASE_HEIGHT)
    };
    private static final Button SUPPORT = new Button("SUPPORT", 1200 / Screen.BASE_WIDTH, 300 / Screen.BASE_HEIGHT);
    private static final Button ATTACK = new Button("ATTACK", 1700 / Screen.BASE_WIDTH, 900 / Screen.BASE_HEIGHT);
    private static final Button[] SKILLS = new Button[] {
            new Button("SKILLS0", 110 / Screen.BASE_WIDTH, 860 / Screen.BASE_HEIGHT),
            new Button("SKILLS1", 250 / Screen.BASE_WIDTH, 860 / Screen.BASE_HEIGHT),
            new Button("SKILLS2", 380 / Screen.BASE_WIDTH, 860 / Screen.BASE_HEIGHT),
            new Button("SKILLS3", 580 / Screen.BASE_WIDTH, 860 / Screen.BASE_HEIGHT),
            new Button("SKILLS4", 720 / Screen.BASE_WIDTH, 860 / Screen.BASE_HEIGHT),
            new Button("SKILLS5", 860 / Screen.BASE_WIDTH, 860 / Screen.BASE_HEIGHT),
            new Button("SKILLS6", 1060 / Screen.BASE_WIDTH, 860 / Screen.BASE_HEIGHT),
            new Button("SKILLS7", 1200 / Screen.BASE_WIDTH, 860 / Screen.BASE_HEIGHT),
            new Button("SKILLS8", 1340 / Screen.BASE_WIDTH, 860 / Screen.BASE_HEIGHT)
    };
    private static final Button MASTER_SKILL_LIST = new Button("MASTER_SKILL_LIST", 1800 / Screen.BASE_WIDTH, 460 / Screen.BASE_HEIGHT);
    private static final Button[] MASTER_SKILLS = new Button[] {
            new Button("MASTER_SKILLS0", 1360 / Screen.BASE_WIDTH, 460 / Screen.BASE_HEIGHT),
            new Button("MASTER_SKILLS1", 1500 / Screen.BASE_WIDTH, 460 / Screen.BASE_HEIGHT),
            new Button("MASTER_SKILLS2", 1630 / Screen.BASE_WIDTH, 460 / Screen.BASE_HEIGHT)
    };
    private static final Button[] HOUGU = new Button[] {
            new Button("HOUGU0", 560 / Screen.BASE_WIDTH, 300 / Screen.BASE_HEIGHT),
            new Button("HOUGU1", 960 / Screen.BASE_WIDTH, 300 / Screen.BASE_HEIGHT),
            new Button("HOUGU2", 1300 / Screen.BASE_WIDTH, 300 / Screen.BASE_HEIGHT)
    };

    private static final Map<String, Button> map;

    static {
        map = new HashMap<>();
        map.put("start", START);
        map.put("support", SUPPORT);
        map.put("next", NEXT);
        map.put("card0", CARDS[0]);
        map.put("card1", CARDS[1]);
        map.put("card2", CARDS[2]);
        map.put("card3", CARDS[3]);
        map.put("card4", CARDS[4]);
        map.put("attack", ATTACK);
        map.put("skill0", SKILLS[0]);
        map.put("skill1", SKILLS[1]);
        map.put("skill2", SKILLS[2]);
        map.put("skill3", SKILLS[3]);
        map.put("skill4", SKILLS[4]);
        map.put("skill5", SKILLS[5]);
        map.put("skill6", SKILLS[6]);
        map.put("skill7", SKILLS[7]);
        map.put("skill8", SKILLS[8]);
        map.put("master_skill_list", MASTER_SKILL_LIST);
        map.put("master_skill0", MASTER_SKILLS[0]);
        map.put("master_skill1", MASTER_SKILLS[1]);
        map.put("master_skill2", MASTER_SKILLS[2]);
        map.put("hougu0", HOUGU[0]);
        map.put("hougu1", HOUGU[1]);
        map.put("hougu2", HOUGU[2]);
    }

    public static Button getButton(String text) {
        return map.get(text);
    }

}
