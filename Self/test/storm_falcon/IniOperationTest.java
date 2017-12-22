package storm_falcon;

import org.junit.jupiter.api.Test;

/**
 * @author gewp
 */
class IniOperationTest {

    private IniOperation ini = new IniOperation();

    private void load() {
        ini.loadIni("C:\\Users\\gesoft\\Desktop\\a.ini");
    }

    @Test
    void getKeyValue() {
        load();
        String value = ini.getKeyValue("Major", "MaxNumber");
        System.out.println(value);
    }

    @Test
    void getSessionValue() {
        load();
        String major = ini.getSectionComment("Epic");
        System.out.println(major);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("asd")));
    }

    @Test
    void setValue() {
        load();
        String value = ini.getKeyValue("Major", "FadeInDuration");
        System.out.println(value);

        ini.setKeyValue("Major", "FadeInDuration", "3");

        value = ini.getKeyValue("Major", "FadeInDuration");
        System.out.println(value);

        ini.saveIni();
    }

    @Test
    void getEntryComment() {
        load();
        String noteValue = ini.getEntryComment(null, "FontDescName");
        System.out.println(noteValue);
    }
}