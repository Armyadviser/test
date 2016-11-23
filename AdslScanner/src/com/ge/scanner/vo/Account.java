package com.ge.scanner.vo;

import com.cp.fields.CpFldMonthHours;
import com.cp.fields.CpFldSlotS;
import com.cp.fields.CpFldVlanId;
import com.ge.scanner.conn.cm.PBaseModule;
import com.portal.pcm.*;
import com.portal.pcm.fields.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Storm_Falcon on 2016/11/7.
 * user vo
 */
public class Account {
    public Poid poid;
    public String login;

	/** 用户编码 */
	public String userId;

	/** 宽带编码 */
	public String mobileNo;

	/** 到期资费编码 */
	public String rpInstId;

    @Override
    public String toString() {
        return "Account{" +
                "poid=" + poid +
                ", login='" + login + '\'' +
                '}';
    }
}
