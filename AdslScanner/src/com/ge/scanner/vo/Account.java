package com.ge.scanner.vo;

import com.portal.pcm.Poid;

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
