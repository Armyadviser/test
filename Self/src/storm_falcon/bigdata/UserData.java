package storm_falcon.bigdata;

import java.util.ArrayList;
import java.util.List;

public class UserData {

	String id;
	
	final List<TimeLocation> allLocList = new ArrayList<>();
	
	public void addTimeLocation(String date, int time, double lat, double lon) {
		TimeLocation tl = new TimeLocation();
		tl.date = date;
		tl.time = time;
		tl.location = new Location(lat, lon);
		
		allLocList.add(tl);
	}
	
	public String toString() {
		return null;
	}
}
