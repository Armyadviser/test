package storm_falcon.vo;

import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.Poid;
import com.portal.pcm.fields.FldContactType;
import com.portal.pcm.fields.FldLastName;

/**
 * @author songyu
 *
 */
public class Account {
	
	public Poid poid;
	public Poid accountObj;
	public String login;
	public String lastname;
	public String identityCode;
	public Integer maxTimeout;
	public Integer monthHours;
	public Integer vlanId;

    /**
     * 暂停标识
     * 0.正常
     * 1.暂停服务
     * 5.欠费停机
     */
    public int pauseSign;

	/**
	 * 用户基本信息数据组合
	 * 用户编码（userId,servid）,
	 * 宽带编码(mobileno),
	 * 到期资费编码(rpinstid)
	 * 3G/4G
	 */
	public String slotS;

	public String city;

	/**
	 * 用户编码
	 */
	public String userId;

	/**
	 * 宽带编码
	 */
	public String mobileNo;

	/**
	 * 到期资费编码
	 */
	public String rpInstId;

	/**
	 * 3g/4g
	 */
	public String netType;

	/**
	 * 0: expire push type, need search bss.
	 * 1: advertisement type, don't need search bss.
	 */
	public int pushType;

    /**
     * 区域编码
     */
    public String area;

    /**
     * 接入类型
     */
    public String accessType;

    public void parseNameInfo(FList nameInfo) {
        try {
            this.lastname = nameInfo.get(FldLastName.getInst());
			this.identityCode = nameInfo.get(FldContactType.getInst());
		} catch (EBufException e) {
			e.printStackTrace();
		}
	}

	public String getExpireTime() {
//		if (maxTimeout == null || maxTimeout.longValue() == 0) {
//			return JTools.getSysTimeStr("yyyy-MM-dd");
//		}
//		return JTools.convertTimeStr(maxTimeout.toString(),
//				"yyyyMMdd", "yyyy-MM-dd");
		return "";
	}

	public String getIdentityCodeSuffix() {
		if (identityCode == null || identityCode.endsWith("]")) {
			return "无";
		}
		return identityCode.substring(identityCode.length() - 4, identityCode.length());
	}

	@Override
	public String toString() {
		return "Account{" +
				"login='" + login + '\'' +
				", lastname='" + lastname + '\'' +
				", city='" + city + '\'' +
				", userId='" + userId + '\'' +
				", mobileNo='" + mobileNo + '\'' +
                ", newYearResId='" + rpInstId + '\'' +
                ", type='" + netType + '\'' +
                '}';
    }
}
