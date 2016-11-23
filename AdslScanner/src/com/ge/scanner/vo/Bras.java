package com.ge.scanner.vo;

import com.cp.fields.CpFldVendorId;
import com.ge.scanner.conn.cm.PBaseModule;
import com.ge.util.string.StringHelper;
import com.portal.pcm.*;
import com.portal.pcm.fields.*;

/**
 * Created by Storm_Falcon on 2016/11/7.
 * bras vo
 */
public class Bras {
    public String ip;
    public String vendorId;
    public String secret;
    public String context;

    @Override
    public String toString() {
        return "Bras{" +
            "ip='" + ip + '\'' +
            ", vendorId='" + vendorId + '\'' +
            ", secret='" + secret + '\'' +
            ", context='" + context + '\'' +
            '}';
    }
}
