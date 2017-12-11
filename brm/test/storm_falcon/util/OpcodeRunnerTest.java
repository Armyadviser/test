package storm_falcon.util;

import com.portal.pcm.FList;
import com.portal.pcm.Poid;
import com.portal.pcm.PortalOp;
import com.portal.pcm.fields.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author gewp
 */
public class OpcodeRunnerTest {

    @Test
    public void runFlist() {
        FList flist = new FList();
        flist.set(FldPoid.getInst(), new Poid(1, -1L, "/search"));
        flist.set(FldFlags.getInst(), 0);

        String strTemplate = "select X from /service/cp_broadband where service_t.poid_type = '/service/cp_broadband' and F1 = V1 ";
        flist.set(FldTemplate.getInst(), strTemplate);

        FList flistArg = new FList();
        flistArg.set(FldLogin.getInst(), "ln_boss_gd_test_ly");
        flist.setElement(FldArgs.getInst(), 1, flistArg);

        FList flistResults = new FList();
        flistResults.set(FldAacSource.getInst());

        flist.setElement(FldResults.getInst(), -1, flistResults);

        System.out.println(flist);
        FList out = OpcodeRunner.execute(flist, PortalOp.SEARCH);
        System.out.println("\n");

        System.out.println(out);

    }
}