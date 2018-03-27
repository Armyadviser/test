package storm_falcon.swing.fgo;

import java.util.HashMap;
import java.util.Map;

public class ButtonManager {

    private static final double BASE_WIDTH = 1200;
    private static final double BASE_HEIGHT = 680;

    private static final Button NEXT = new Button(1010, 620);
    private static final Button[] CARDS = new Button[] {
            new Button(130, 500), new Button(350, 490),
            new Button(630, 510), new Button(830, 500),
            new Button(1090, 520)};
    private static final Button SUPPORT = new Button(640, 220);
    private static final Button ATTACK = new Button(1010, 620);
    private static final Button[] SKILLS = new Button[] {
            new Button(60, 540), new Button(155, 540), new Button(245, 540),
            new Button(360, 550), new Button(450, 550), new Button(540, 550),
            new Button(660, 550), new Button(750, 550), new Button(840, 550)};
    private static final Button MASTER_SKILL_LIST = new Button(1120, 300);
    private static final Button[] MASTER_SKILLS = new Button[] {
            new Button(850, 300), new Button(940, 300), new Button(1020, 300)
    };
    private static final Button[] HOUGU = new Button[] {
            new Button(350, 240), new Button(600, 200), new Button(840, 210)
    };

    private static final Map<String, Button> map;

    static {
        map = new HashMap<>();
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

    static class Button {
        private int x;
        private int y;

        Button(int x, int y) {
            this.x = x;
            this.y = y;
        }

        double getXRadius() {
            return x / BASE_WIDTH;
        }

        double getYRadius() {
            return y / BASE_HEIGHT;
        }
    }
}
