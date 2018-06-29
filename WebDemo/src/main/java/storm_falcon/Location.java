package storm_falcon;

import java.awt.geom.Point2D;

public class Location {

    static final double MAX_LONGITUDE = 180;
    static final double MIN_LONGITUDE = -180;
    static final double MAX_LATITUDE = 90;
    static final double MIN_LATITUDE = -90;

    public String id;
    double longitude;
    double latitude;

    Location(String id, double longitude, double latitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    String toJson() {
        return "{\"id\":\"" + id + "\",\"lon\":" +
                longitude + ",\"lat\":" + latitude + "}";
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    public static double getDistance(Location l1, Location l2) {
        return Wv(new Point2D.Double(l1.longitude, l1.latitude),
                new Point2D.Double(l2.longitude, l2.latitude));
    }

    private static double Wv(Point2D.Double a, Point2D.Double b) {
        if (a == null || b == null) {
            return 0;
        }

        return Td(oi(a.x), oi(b.x), oi(a.y), oi(b.y));
    }

    private static double oi(double a) {
        return Math.PI * a / 180;
    }

    private static double Td(double a, double b, double c, double d) {
        return 6371393D * Math.acos(Math.sin(c) * Math.sin(d) + Math.cos(c) * Math.cos(d) * Math.cos(b - a));
    }
}
