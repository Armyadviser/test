package storm_falcon.swing.fgo;

import storm_falcon.IniOperation;
import storm_falcon.swing.fgo.round.Round;

import java.util.List;

public class SaoCaoZuo {

    private List<Round> saoCaoZuo;

    private SaoCaoZuo() {
        init(getClass().getResource("action.ini").getPath());
    }

    private void init(String path) {
        IniOperation ini = new IniOperation();
        ini.loadIni(path);

        for (int i = 1; ; i++) {
            String roundType = ini.getKeyValue("rounds", "round" + i);
            if (roundType == null) {
                break;
            }

            Round round = Round.parse(ini, i);
            saoCaoZuo.add(round);
        }
    }

    private static SaoCaoZuo instance;

    public static SaoCaoZuo getInstance() {
        if (instance == null) {
            instance = new SaoCaoZuo();
        }
        return instance;
    }

    public Round getRound(int n) {
        return saoCaoZuo.get(n);
    }
}
