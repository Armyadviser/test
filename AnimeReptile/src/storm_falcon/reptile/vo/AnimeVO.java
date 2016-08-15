package storm_falcon.reptile.vo;

import storm_falcon.util.html.HtmlHelper;

/**
 * Created by Storm_Falcon on 2016/8/15.
 * '圖片','動畫名','直接搜索連結','字幕組','官方公式'
 */
public class AnimeVO {
    private String imgUrl;
    public String name;
    public String keyword;
    private String teamId;
    private String webSite;

    public static AnimeVO parse(String line) {
        String arrayContent = HtmlHelper.getCenterContent(line, "([", "])");
        arrayContent = arrayContent.replaceAll("\'", "");

        //拆分，结果:图片url、番名、关键字、关键字+各字幕组id、官网
        String[] items = arrayContent.split(",");
        AnimeVO vo = new AnimeVO();
        vo.imgUrl = items[0];
        vo.name = items[1];
        vo.keyword = items[2];
        vo.teamId = items[3];
        vo.webSite = items[4];

        return vo;
    }

    @Override
    public String toString() {
        return "AnimeVO{" +
                "imgUrl='" + imgUrl + '\'' +
                ", name='" + name + '\'' +
                ", keyword='" + keyword + '\'' +
                ", teamId='" + teamId + '\'' +
                ", webSite='" + webSite + '\'' +
                '}';
    }
}
