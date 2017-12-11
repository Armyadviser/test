package storm_falcon;

import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.Poid;
import com.portal.pcm.PortalOp;
import com.portal.pcm.fields.*;
import storm_falcon.fields.*;
import storm_falcon.util.CityUtil;
import storm_falcon.util.FileWriter;
import storm_falcon.util.OpcodeRunner;
import storm_falcon.vo.Account;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gewp
 */
public class RedirectNumberSummery {
    /**
     * 根据宽带账号查询用户信息
     *
     * @param strAdsl 宽带账号
     * @return Account对象
     */
    public static Account getAccount(String strAdsl) {

        FList flist = new FList();
        flist.set(FldPoid.getInst(), new Poid(1, -1L, "/search"));
        flist.set(FldFlags.getInst(), 0);

        String strTemplate = "select X from /service/cp_broadband where service_t.poid_type = '/service/cp_broadband' and F1 = V1 ";
        flist.set(FldTemplate.getInst(), strTemplate);

        FList flistArg = new FList();
        flistArg.set(FldLogin.getInst(), strAdsl);
        flist.setElement(FldArgs.getInst(), 1, flistArg);

        FList flistResults = new FList();

        FList serviceIp = new FList();
        serviceIp.set(CpFldMaxTimeout.getInst());
        serviceIp.set(CpFldMonthHours.getInst());
        serviceIp.set(CpFldVlanId.getInst());
        serviceIp.set(CpFldSlotS.getInst());
        serviceIp.set(CpFldSubStateFlag.getInst());

        flistResults.set(FldServiceIp.getInst(), serviceIp);
        flistResults.set(FldAccountObj.getInst());
        flistResults.set(FldAacSource.getInst());

        flist.setElement(FldResults.getInst(), -1, flistResults);

        //MBaseModel.SHOW_DEBUG = true;
        FList flistOut = OpcodeRunner.execute(flist, PortalOp.SEARCH);

        if (flistOut == null || !flistOut.hasField(FldResults.getInst())) {
            return null;
        }

        try {
            FList flistResult = flistOut.getElement(FldResults.getInst(), 0);
            serviceIp = flistResult.get(FldServiceIp.getInst());

            Poid mServicePoid = flistResult.get(FldPoid.getInst());
            Poid accountObj = flistResult.get(FldAccountObj.getInst());
            String city = flistResult.get(FldAacSource.getInst());
            Integer iMaxTimeout = serviceIp.get(CpFldMaxTimeout.getInst());
            Integer iMonthHours = serviceIp.get(CpFldMonthHours.getInst());
            Integer iVlanId = serviceIp.get(CpFldVlanId.getInst());
            String sSlotS = serviceIp.get(CpFldSlotS.getInst());

            Account account = new Account();
            account.poid = mServicePoid;
            account.accountObj = accountObj;
            account.city = city;
            account.login = strAdsl;
            account.maxTimeout = iMaxTimeout;
            account.monthHours = iMonthHours;
            account.vlanId = iVlanId;
            account.slotS = sSlotS;
            account.pauseSign = serviceIp.get(CpFldSubStateFlag.getInst());
            if (sSlotS != null && sSlotS.length() != 0) {
                String[] items = sSlotS.split(",");
                if (items.length >= 4) {
                    account.userId = items[0];
                    account.mobileNo = items[1];
                    account.rpInstId = items[2];
                    account.netType = items[3];
                }
                if (items.length >= 6) {
                    account.area = items[4];
                    account.accessType = items[5];
                }
            }

            return account;
        } catch (EBufException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String parseLogin(String s) {
        try {
            int start = s.indexOf("Login:");
            int end = s.indexOf(", City");
            return s.substring(start + 6, end);
        } catch (Exception e) {
            System.err.println(s);
        }
        return null;
    }

    public static String getDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(date);
    }

    public static String getCity(String login) {
        try {
            Account account = getAccount(login);
            if (account != null) {
                return account.city;
            } else {
                System.out.println("null:" + login);
            }
        } catch (Exception e) {
            System.out.println(login);
            e.printStackTrace();
        }
        return null;
    }

    public static void test() throws Exception {
        Stream<String> stream = Stream.empty();
        String dir2 = "/home/falcon/test/log.2/";
        String dir3 = "/home/falcon/test/log.3/";
        for (LocalDate i = LocalDate.of(2017, 12, 10);
             i.isBefore(LocalDate.of(2017, 12, 11));
             i = i.plusDays(1L)) {
            String path2 = dir2 + getDate(i) + ".log";
            String path3 = dir3 + getDate(i) + ".log";
            stream = Stream.concat(stream, Files.lines(Paths.get(path2)));
            stream = Stream.concat(stream, Files.lines(Paths.get(path3)));
        }

        FileWriter writer = new FileWriter();
        writer.open("/home/falcon/temp/宽带缴费页面访问帐号清单20171210.txt");

        Map<String, List<AbstractMap.SimpleEntry<String, String>>> map = stream
                .filter(s -> s.contains("AdOffline"))
                .filter(s -> !s.contains("null"))
                .map(RedirectNumberSummery::parseLogin)
                .filter(Objects::nonNull)
                .distinct()
//                .peek(System.out::println)
                .peek(writer::writeLine)
                .map(login -> new AbstractMap.SimpleEntry<>(getCity(login), login))
                .filter(entry -> entry.getKey() != null)
                .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey));

        Map<String, Integer> result = CityUtil.convertResult(map);
        List<Map.Entry<String, Integer>> sorted = CityUtil.convertCity(result);
        sorted.forEach(entry -> System.out.println(entry.getKey() + "    " + entry.getValue()));

        writer.close();
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("user.dir", "/home/falcon/IdeaProjects/test/Self/");
        test();

    }
}
