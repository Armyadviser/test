package storm_falcon.reptile;

import storm_falcon.reptile.vo.AnimeVO;
import storm_falcon.util.Log;
import storm_falcon.util.file.FileHelper;
import storm_falcon.util.html.HtmlHelper;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Storm_Falcon on 2016/8/15.
 * 打开某个具体动漫页面并下载种子的线程
 * 执行过程中会启动多个该线程
 */
class SubPageThread extends Thread {

    /**
     * 某个具体动漫的url
     *   http://share.dmhy.org/topics/list?keyword=...
     */
    private String url = null;
    private String torrentName;
    private AnimeVO mVO;

    SubPageThread(AnimeVO vo) {
        url = AnimeReptile.HOST + AnimeReptile.SEARCH_URL + vo.keyword;
        torrentName = AnimeReptile.BASE_PATH + vo.name + ".torrent";
        mVO = vo;
    }

    @Override
    public void run() {
        //解析url的页面
        String content = HtmlHelper.getHtmlContent(url, "utf-8");
        String tableInfo = HtmlHelper.getCenterContent(1, content, "<tbody>", "</tbody>");

        Optional<String> urlOption = Arrays.stream(tableInfo.split("</tr>"))
                .filter(tr -> {
                    String time = HtmlHelper.getCenterContent(tr, "<td", "</td>");
                    return time.contains("今天") || time.contains("昨天");
                }).filter(tr -> {
                    String urlTd = HtmlHelper.getCenterContent(3, tr, "<td", "</td>");
                    return urlTd.contains("GB") || urlTd.contains("BIG5")
                        || urlTd.contains("简") || urlTd.contains("繁");
                }).map(tr -> {
                    String urlTd = HtmlHelper.getCenterContent(3, tr, "<td", "</td>");
                    String subUrl = HtmlHelper.getCenterContent(urlTd, "href=\"", "\"");
                    if (!url.endsWith(".html")) {//不以html结尾说明是字幕组信息
                        subUrl = HtmlHelper.getCenterContent(2, urlTd, "href=\"", "\"");
                    }
                    return subUrl;
                }).findFirst();

        String animeUrl;
        if (urlOption.isPresent()) {
            animeUrl = urlOption.get();
        } else {
            Log.err("未获取到" + mVO.name + "的信息。");
            return;
        }
        String torrentContent = HtmlHelper.getHtmlContent(AnimeReptile.HOST + animeUrl, "utf-8");

        //截取种子url
        String temp = HtmlHelper.getCenterContent(torrentContent, "href=\"", ".torrent");
        String torrent = temp.substring(temp.lastIndexOf("href=\"") + 6, temp.length());
        torrent = "http:" + torrent + ".torrent";

        FileHelper.download(torrent, torrentName);

        Log.debug(torrentName + "下载成功！");


//        String[] trs = tableInfo.split("</tr>");
//
//        for (String tr : trs) {
//            String time = HtmlHelper.getCenterContent(tr, "<td", "</td>");
//            if (!time.contains("今天") && !time.contains("昨天")) {
//                continue;
//            }
//
//            String urlTd = HtmlHelper.getCenterContent(3, tr, "<td", "</td>");
//            if (!urlTd.contains("GB") && !urlTd.contains("BIG5")
//                    && !urlTd.contains("简") && !urlTd.contains("繁")) {
//                continue;
//            }
//
//            //获取当天更新的动漫的url
//            String subUrl = HtmlHelper.getCenterContent(urlTd, "href=\"", "\"");
//            if (!url.endsWith(".html")) {//不以html结尾说明是字幕组信息
//                subUrl = HtmlHelper.getCenterContent(2, urlTd, "href=\"", "\"");
//            }
//
//            //获取大小，单位：MB
//            String sizeInfo = HtmlHelper.getCenterContent(5, tr, "<td", "</td>");
//            int tag = sizeInfo.lastIndexOf(">");
//            String size = sizeInfo.substring(tag + 1, sizeInfo.length() - 2);


            //筛选最新更新的动漫

    }
}
