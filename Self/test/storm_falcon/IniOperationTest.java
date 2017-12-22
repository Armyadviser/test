package storm_falcon;

import org.junit.Test;

/**
 * @author gewp
 */
public class IniOperationTest {

    private IniOperation ini = new IniOperation();

    private void load() {
        ini.loadIni("C:\\Users\\gesoft\\Desktop\\a.ini");
    }

    @Test
    public void getKeyValue() {
        load();
        String value = ini.getKeyValue("Major", "MaxNumber");
        System.out.println(value);
    }

    @Test
    public void getSessionValue() {
        load();
        String major = ini.getSectionComment("Epic");
        System.out.println(major);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("asd")));
    }

    @Test
    public void setValue() {
        load();
        String value = ini.getKeyValue("Major", "FadeInDuration");
        System.out.println(value);

        ini.setKeyValue("Major", "FadeInDuration", "3");

        value = ini.getKeyValue("Major", "FadeInDuration");
        System.out.println(value);
    }

    @Test
    public void save() {
        ini.loadIni("/home/falcon/test/a.ini");
        ini.setKeyValue("se", "age", "23");
        ini.saveIni();
    }

    @Test
    public void getEntryComment() {
        load();
        String noteValue = ini.getEntryComment(null, "FontDescName");
        System.out.println(noteValue);
    }
}