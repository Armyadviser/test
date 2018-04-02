package storm_falcon.swing.fgo;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ButtonManager {

    private static final Button START = new Button("START", 1660, 960, 230, 100);
    private static final Button NEXT = new Button("NEXT", 1420, 960, 490, 120);
    private static final Button[] CARDS = new Button[] {
            new Button("CARD0", 80, 620, 220, 300),
            new Button("CARD1", 460, 620, 220, 300),
            new Button("CARD2", 850, 620, 220, 300),
            new Button("CARD3", 1240, 620, 220, 300),
            new Button("CARD4", 1620, 620, 220, 300)
    };
    private static final Button DUNGEON_SELECT = new Button("DUNGEON_SELECT", 940 , 170, 920, 220);
    private static final Button SUPPORT = new Button("SUPPORT", 70 , 290, 1550, 270);
    private static final Button ATTACK = new Button("ATTACK", 1600 , 820, 200, 200);
    private static final Button[] SKILLS = new Button[] {
            new Button("SKILLS0", 40, 810, 100, 100),
            new Button("SKILLS1", 190, 810, 100, 100),
            new Button("SKILLS2", 330, 810, 100, 100),
            new Button("SKILLS3", 520, 810, 100, 100),
            new Button("SKILLS4", 670, 810, 100, 100),
            new Button("SKILLS5", 810, 810, 100, 100),
            new Button("SKILLS6", 1000, 810, 100, 100),
            new Button("SKILLS7", 1150, 810, 100, 100),
            new Button("SKILLS8", 1300, 810, 100, 100),
    };
    private static final Button MASTER_SKILL_LIST = new Button("MASTER_SKILL_LIST", 1720 , 400, 120, 120);
    private static final Button[] MASTER_SKILLS = new Button[] {
            new Button("MASTER_SKILLS0", 1310, 420, 100, 100),
            new Button("MASTER_SKILLS1", 1440, 420, 100, 100),
            new Button("MASTER_SKILLS2", 1580, 420, 100, 100)
    };
    private static final Button[] HOUGU = new Button[] {
            new Button("HOUGU0", 510, 160, 220, 300),
            new Button("HOUGU1", 860, 160, 220, 300),
            new Button("HOUGU2", 1200, 170, 220, 300)
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
        map.put("dungeon_select", DUNGEON_SELECT);
    }

    public static Button getButton(String text) {
        return map.get(text);
    }

}
