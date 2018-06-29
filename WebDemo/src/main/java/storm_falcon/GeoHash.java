package storm_falcon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeoHash {

    private Location location;
    /**
     * 1 2500km;2 630km;3 78km;4 30km
     * 5 2.4km; 6 610m; 7 76m; 8 19m
     */
    private int hashLength = 8; //经纬度转化为geohash长度
    private int latLength = 20; //纬度转化为二进制长度
    private int lngLength = 20; //经度转化为二进制长度

    private double minLng;//每格经度的单位大小
    private double minLat;//每格纬度的单位大小
    private static final char[] CHARS = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public GeoHash(Location location) {
        this.location = location;
        setMinLngLat();
    }

    public GeoHash(double lng, double lat) {
        this(new Location(lng, lat));
    }

    public int getHashLength() {
        return hashLength;
    }

    /**
     * 设置经纬度的最小单位
     */
    private void setMinLngLat() {
        minLat = Location.MAX_LATITUDE - Location.MIN_LATITUDE;
        for (int i = 0; i < latLength; i++) {
            minLat /= 2.0;
        }
        minLng = Location.MAX_LONGITUDE - Location.MIN_LONGITUDE;
        for (int i = 0; i < lngLength; i++) {
            minLng /= 2.0;
        }
    }

    /**
     * 求所在坐标点及周围点组成的九个
     */
    public List<String> getGeoHashBase32For9() {
        double leftLat = location.latitude - minLat;
        double rightLat = location.latitude + minLat;
        double upLng = location.longitude - minLng;
        double downLng = location.longitude + minLng;
        List<String> base32For9 = new ArrayList<>();

        //左侧从上到下 3个
        String leftUp = getGeoHashBase32(upLng, leftLat);
        if (!"".equals(leftUp)) {
            base32For9.add(leftUp);
        }
        String leftMid = getGeoHashBase32(location.longitude, leftLat);
        if (!"".equals(leftMid)) {
            base32For9.add(leftMid);
        }
        String leftDown = getGeoHashBase32(downLng, leftLat);
        if (!"".equals(leftDown)) {
            base32For9.add(leftDown);
        }
        //中间从上到下 3个
        String midUp = getGeoHashBase32(upLng, location.latitude);
        if (!"".equals(midUp)) {
            base32For9.add(midUp);
        }
        String midMid = getGeoHashBase32(location.longitude, location.latitude);
        if (!"".equals(midMid)) {
            base32For9.add(midMid);
        }
        String midDown = getGeoHashBase32(downLng, location.latitude);
        if (!"".equals(midDown)) {
            base32For9.add(midDown);
        }
        //右侧从上到下 3个
        String rightUp = getGeoHashBase32(upLng, rightLat);
        if (!"".equals(rightUp)) {
            base32For9.add(rightUp);
        }
        String rightMid = getGeoHashBase32(location.longitude, rightLat);
        if (!"".equals(rightMid)) {
            base32For9.add(rightMid);
        }
        String rightDown = getGeoHashBase32(downLng, rightLat);
        if (!"".equals(rightDown)) {
            base32For9.add(rightDown);
        }
        return base32For9;
    }

    /**
     * 设置经纬度转化为geoHash长度
     */
    public boolean setHashLength(int length) {
        if (length < 1) {
            return false;
        }
        hashLength = length;
        latLength = (length * 5) / 2;
        if (length % 2 == 0) {
            lngLength = latLength;
        } else {
            lngLength = latLength + 1;
        }
        setMinLngLat();
        return true;
    }

    /**
     * 获取经纬度的base32字符串
     */
    public String getGeoHashBase32() {
        return getGeoHashBase32(location.longitude, location.latitude);
    }

    /**
     * 获取经纬度的base32字符串
     */
    private String getGeoHashBase32(double lng, double lat) {
        boolean[] bools = getGeoBinary(lng, lat);
        if (bools.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bools.length; i = i + 5) {
            boolean[] base32 = new boolean[5];
            // 每5位二进制转化成一个字符
//            for (int j = 0; j < 5; j++) {
//                base32[j] = bools[i + j];
//            }
            System.arraycopy(bools, i, base32, 0, 5);
            char cha = getBase32Char(base32);
            if (' ' == cha) {
                return "";
            }
            sb.append(cha);
        }
        return sb.toString();
    }

    /**
     * 将五位二进制转化为base32
     */
    private char getBase32Char(boolean[] base32) {
        if (base32 == null || base32.length != 5) {
            return ' ';
        }
        int num = 0;
        for (boolean bool : base32) {
            num <<= 1;
            if (bool) {
                num += 1;
            }
        }
        return CHARS[num % CHARS.length];
    }

    /**
     * 获取坐标的geo二进制字符串
     */
    private boolean[] getGeoBinary(double lng, double lat) {
        boolean[] lngArray = getHashArray(lng, Location.MIN_LONGITUDE, Location.MAX_LONGITUDE, lngLength);
        boolean[] latArray = getHashArray(lat, Location.MIN_LATITUDE, Location.MAX_LATITUDE, latLength);
        return merge(lngArray, latArray);
    }

    /**
     * 合并经纬度二进制
     * 偶数位放经度
     * 奇数位放纬度
     */
    private boolean[] merge(boolean[] lngArray, boolean[] latArray) {
        if (latArray.length == 0 || lngArray.length == 0) {
            return new boolean[0];
        }

        boolean[] result = new boolean[lngArray.length + latArray.length];
        Arrays.fill(result, false);
        for (int i = 0; i < lngArray.length; i++) {
            result[2 * i] = lngArray[i];
        }
        for (int i = 0; i < latArray.length; i++) {
            result[2 * i + 1] = latArray[i];
        }
        return result;
    }

    /**
     * 将经纬度转化为GeoHash二进制字符串
     * @param value 经纬度数
     * @param min 区间最小值
     * @param max 区间最大值
     * @param length 二进制结果长度
     * @return 以boolean数组形式表示的二进制字符串
     */
    private boolean[] getHashArray(double value, double min, double max, int length) {
        if (value < min || value > max) {
            return new boolean[0];
        }
        if (length < 1) {
            return new boolean[0];
        }

        boolean[] result = new boolean[length];
        for (int i = 0; i < length; i++) {
            double mid = (min + max) / 2.0;
            if (value > mid) {
                result[i] = true;
                min = mid;
            } else {
                result[i] = false;
                max = mid;
            }
        }
        return result;
    }

}