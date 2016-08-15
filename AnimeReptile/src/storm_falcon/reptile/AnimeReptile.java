package storm_falcon.reptile;

import storm_falcon.reptile.vo.AnimeVO;
import storm_falcon.util.file.FileReader;
import storm_falcon.util.html.HtmlHelper;

import java.io.File;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Storm_Falcon on 2016/8/15.
 *
 */
public class AnimeReptile {

    static final String SEARCH_URL = "topics/list?keyword=";

    static final String HOST = "http://share.dmhy.org/";

    static final String BASE_PATH = System.getProperty("user.dir") + File.separatorChar;

    private static final String iniFileName = "List2Download.ini";

    private Stream<AnimeVO> getAnimes() {
        String content = HtmlHelper.getHtmlContent(HOST, "utf-8");
        return Arrays.stream(content.split("\n"))
                .filter(line -> line.contains("array.push"))
                .map(AnimeVO::parse);
    }

    /**
     * 从所有动漫中筛选出关注的动漫
     * @param animeInfos 一周7天所有动漫信息
     * @param animeFocus 关注的动漫
     * @return
     */
    private Stream<AnimeVO> filter(Stream<AnimeVO> animeInfos, Set<String> animeFocus) {
        return animeInfos.filter(vo -> animeFocus.contains(vo.name));
    }

    /**
     * @param args
     *  [0].动漫名
     *  [1].话数
     *  如果不加参数，则下载今天更新的所有动漫
     */
    public static void main(String[] args) {
//        String name = null, episode = null;
//        if (args.length != 0) {
//            name = args[0];
//            episode = args[1];
//        }

        AnimeReptile reptile = new AnimeReptile();

        //解析主页的所有动漫信息
        Stream<AnimeVO> animeInfos = reptile.getAnimes();

        //读取配置文件，要下载的动漫名
        Set<String> animeFocus = FileReader.mapForEach(BASE_PATH + iniFileName, "utf-8")
                .collect(Collectors.toSet());

        //过滤掉配置文件中没有的
        Stream<AnimeVO> toDownloadAnimes = reptile.filter(animeInfos, animeFocus);

        toDownloadAnimes.forEach(animeVO ->
                new SubPageThread(animeVO).start());

    }
}
