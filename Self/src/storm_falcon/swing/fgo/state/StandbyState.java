package storm_falcon.swing.fgo.state;

import storm_falcon.swing.fgo.SaoCaoZuo;
import storm_falcon.swing.fgo.Screen;
import storm_falcon.swing.fgo.round.RandomRound;
import storm_falcon.swing.fgo.round.Round;
import storm_falcon.util.image.ImageHelper;
import storm_falcon.util.ocr.OCRHelper;

public class StandbyState extends State {

    @Override
    public void proceed() {
        String roundImg = ImageHelper.subImage("D:/test",
                 Screen.ROUND_RECT.getRectangle());
        String text = OCRHelper.recognizeText(roundImg);
        if (!text.contains("回合")) {
            System.err.println("recognize round error." + roundImg);
            return;
        }

        int nRound = Integer.parseInt(text.substring(0, 1));
        Round round = SaoCaoZuo.getInstance().getRound(nRound);
        round.proceed();
    }
}
