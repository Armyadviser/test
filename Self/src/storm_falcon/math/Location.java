package storm_falcon.math;

import java.awt.geom.Point2D;

public class Location {
    public String id;
    public double longitude;
    public double latitute;

    public Location(String id, double longitude, double latitute) {
        this.id = id;
        this.longitude = longitude;
        this.latitute = latitute;
    }

    public Location(double longitude, double latitute) {
        this.longitude = longitude;
        this.latitute = latitute;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", longitude=" + longitude +
                ", latitute=" + latitute +
                '}';
    }

    public static double getDistance(Location l1, Location l2) {
        return Wv(new Point2D.Double(l1.longitude, l1.latitute),
                new Point2D.Double(l2.longitude, l2.latitute));
    }

    private static double Wv(Point2D.Double a, Point2D.Double b) {
        if (a == null || b == null) {
            return 0;
        }

//        double lat1 = a.y;
//        double lat2 = b.y;
//        double lon1 = a.x;
//        double lon2 = b.x;
//
//        double hsinX = Math.sin((lon1 - lon2) * 0.5);
//        double hsinY = Math.sin((lat1 - lat2) * 0.5);
//        double h = hsinY * hsinY +
//                (Math.cos(lat1) * Math.cos(lat2) * hsinX * hsinX);
//        return 2 * Math.atan2(Math.sqrt(h), Math.sqrt(1 - h)) * 6370996.81D;

        return Td(oi(a.x), oi(b.x), oi(a.y), oi(b.y));
    }

    private static double oi(double a) {
        return Math.PI * a / 180;
    }

    private static double Td(double a, double b, double c, double d) {
        return 6371393D * Math.acos(Math.sin(c) * Math.sin(d) + Math.cos(c) * Math.cos(d) * Math.cos(b - a));
    }

    private static double ew(double a) {
//        if (a > 180D) {
//            a -= 360D;
//        } else if (a < -180D) {
//            a += 360D;
//        }
        return a;
    }

    private static double lw(double a) {
//        a = max(a, -74D);
//        a = min(a, 74D);
        return a;
    }

    private static double max(double a, double b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    private static double min(double a, double c) {
        if (a > c) {
            return c;
        }
        return a;
    }

}
