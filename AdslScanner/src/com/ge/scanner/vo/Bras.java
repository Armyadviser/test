package com.ge.scanner.vo;

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
