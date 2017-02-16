package storm_falcon.bigdata;

public class Location {

	public double latitute;
	public double longitute;
	
	public Location() {}

	public Location(String loc) {
		String[] item = loc.split(",");
		this.latitute = Double.parseDouble(item[0]);
		this.longitute = Double.parseDouble(item[1]);
	}

	public Location(double lat, double lon) {
		latitute = lat;
		longitute = lon;
	}
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof Location)) {
			return false;
		}
		Location l = (Location) o;
		return latitute == l.latitute && longitute == l.longitute;
	}
	
	public String toString() {
		return latitute + "," + longitute;
	}
	
	public double getDistance(Location other) {
		double a, b, R;  
		R = 6378137; // 地球半径  
		double lat1 = latitute * Math.PI / 180.0;  
		double lat2 = other.latitute * Math.PI / 180.0;  
		a = lat1 - lat2;  
		b = (longitute - other.longitute) * Math.PI / 180.0;  
		double d;  
		double sa2, sb2;  
		sa2 = Math.sin(a / 2.0);  
		sb2 = Math.sin(b / 2.0);  
		d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
				* Math.cos(lat2) * sb2 * sb2));  
	    return d;  
	}
	
}
