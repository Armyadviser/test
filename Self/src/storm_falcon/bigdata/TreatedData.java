package storm_falcon.bigdata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class TreatedData {
	
	public String userId;

	/**
	 * 所有点数
	 */
	public int pointNum;

	public Location home;
	public int homeNum;

	public Location company;
	public int companyNum;

	public List<Location> businessDistrict = new ArrayList<>();
	public int businessNum;

	public List<Location> other = new ArrayList<>();

	public static TreatedData parseUserData(UserData user) {
		if (user == null || user.id == null) {
			return null;
		}
		TreatedData td = new TreatedData();
		td.userId = user.id;
		
		List<TimeLocation> tlList = user.allLocList;
		
		//先将所有点都设为other
		parseOther(td, tlList);
		
		//筛选home，并从other中删除
		parseHome(td, tlList);
		
		//筛选公司，并从other中删除
		parseCompany(td, tlList);
		
		//筛选商圈，并从other中删除
		parseBusinessDistrict(td, tlList);

		td.other = duplicate(td.other, 200);
		
		return td;
	}

	public static TreatedData parseString(String line) {
		try {
			String[] item = line.split("\\t");
			if (item.length < 9) {
				return null;
			}

			TreatedData td = new TreatedData();
			td.userId = item[0];//用户ID
			td.pointNum = Integer.parseInt(item[1]);//总点数
			if (!"null".equals(item[2])) {
				td.home = new Location(item[2]);//家位置，121.2839,31.34085001
			}
			td.homeNum = Integer.parseInt(item[3]);//家点数
			if (!"null".equals(item[4])) {
				td.company = new Location(item[4]);//公司位置
			}
			td.companyNum = Integer.parseInt(item[5]);//公司点数
			td.businessDistrict = parseLocationListString(item[6]);
			td.businessNum = Integer.parseInt(item[7]);
			td.other = parseLocationListString(item[8]);
			return td;
		} catch (Exception e) {
			System.out.println(line);
			e.printStackTrace();
		}
		return null;
	}

	private static void parseOther(TreatedData td, List<TimeLocation> tlList) {
		td.other.addAll(tlList.stream().map(tl -> tl.location).collect(Collectors.toList()));
		td.pointNum = td.other.size();
	}

	/**
	 * @param string [lat1,long1, lat2,long2...]
	 * @return List<Location>([Location(lat1, long1), Location(lat2, long2)])
	 */
	private static List<Location> parseLocationListString(String string) {
		string = string.substring(1, string.length() - 1);
		if (string.length() == 0)
			return new ArrayList<>();
		return Arrays.stream(string.split(", ")).map(Location::new).collect(Collectors.toList());
	}
	
	private static void parseHome(TreatedData td, List<TimeLocation> tlList) {
		//从所有点中选出晚上的点
		List<TimeLocation> nightLoc = selectLocationByGivenTime(tlList, new int[][]{{22, 24}, {0, 6}});
		
		//对这些点计数，选出数量最多的点作为家
		countHome(td, nightLoc);
		
		//从other中删掉home
		removeHomeFromOther(td);
	}
	
	private static void parseCompany(TreatedData td, List<TimeLocation> tlList) {
		//从所有点中选出工作时间的点
		List<TimeLocation> workLoc = selectLocationByGivenTime(tlList, new int[][]{{9, 11}, {14, 16}});
		
		//进一步筛选非周末的点
		workLoc = selectLocationIfWeekend(workLoc, false);
	
		//对这些点计数，选出数量最多的点作为公司
		countCompany(td, workLoc);
		
		//从other中删掉company
		removeCompanyFromOther(td);
	}

	private static void parseBusinessDistrict(TreatedData td,
											  List<TimeLocation> tlList) {
		//筛选白天时间的点
		List<TimeLocation> busLoc = selectLocationByGivenTime(tlList, new int[][]{{8, 22}});
		
		//进一步筛选周末的点
		busLoc = selectLocationIfWeekend(busLoc, true);
		
		//对这些点计数，数量大于3的点作为商圈
		countBusDist(td, busLoc);
		
		//从other中删掉商圈
		removeBusDistFromOther(td);
	}

	/**
	 * 筛选特定时间的点
	 * @param tlList
	 * @param time n行2列数组<br>
	 * 		[0]开始时间<br>
	 * 		[1]结束时间
	 * @return
	 */
	private static List<TimeLocation> selectLocationByGivenTime(List<TimeLocation> tlList,
			int [][] time) {
		List<TimeLocation> resList = new ArrayList<>();
		for (TimeLocation tl : tlList) {
			for (int[] aTime : time) {
				if (tl.time >= aTime[0]
						&& tl.time <= aTime[1]) {
					resList.add(tl);
				}
			}
		}
		return resList;
	}
	
	/**
	 * 筛选指定日期的点
	 * @param tlList
	 * @param isWeekend 是否为周末
	 * @return
	 */
	private static List<TimeLocation> selectLocationIfWeekend(List<TimeLocation> tlList,
			boolean isWeekend) {
		return tlList.stream().filter(tl -> isWeekend(tl.date) == isWeekend).collect(Collectors.toList());
	}
	
	private static boolean isWeekend(String sDate) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMMdd").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		assert date != null;
		c.setTime(date);
		int day = c.get(Calendar.DAY_OF_WEEK);
		return day == Calendar.SATURDAY || day == Calendar.SUNDAY;
	}

	private static void removeHomeFromOther(TreatedData td) {
		boolean flag = true;
		while (flag) {
			flag = td.other.remove(td.home);
		}
	}
	
	private static void removeCompanyFromOther(TreatedData td) {
		boolean flag = true;
		while (flag) {
			flag = td.other.remove(td.company);
		}
	}
	
	private static void removeBusDistFromOther(TreatedData td) {
		td.other.removeAll(td.businessDistrict);
	}

	private static void countHome(TreatedData td, List<TimeLocation> locList) {
		if (locList.size() == 0) {
			return;
		}
		Map<String, Integer> map = new HashMap<>();
		
		Iterator<TimeLocation> iter = locList.iterator();
		int max = 0;
		td.home = locList.get(0).location;
		while (iter.hasNext()) {
			Location loc = iter.next().location;
			String key = loc.toString();
			Integer num = map.get(key);
			if (num == null) {
				num = 1;
				map.put(key, num);
			} else {
				map.put(key, ++num);
			}
			if (num > max) {
				td.home = loc;
				max = num;
			}
		}
		td.homeNum = max;
	}
	
	private static void countCompany(TreatedData td, List<TimeLocation> locList) {
		if (locList.size() == 0) {
			return;
		}
		Map<String, Integer> map = new HashMap<>();
		
		Iterator<TimeLocation> iter = locList.iterator();
		int max = 0;
		td.company = locList.get(0).location;
		while (iter.hasNext()) {
			Location loc = iter.next().location;
			String key = loc.toString();
			Integer num = map.get(key);
			if (num == null) {
				num = 1;
				map.put(key, num);
			} else {
				map.put(key, ++num);
			}
			if (num > max) {
				td.company = loc;
				max = num;
			}
		}
		td.companyNum = max;
	}
	
	private static void countBusDist(TreatedData td, List<TimeLocation> busLoc) {
		Map<String, Integer> map = new HashMap<>();

		for (TimeLocation aBusLoc : busLoc) {
			Location loc = aBusLoc.location;
			String key = loc.toString();
			Integer num = map.get(key);
			if (num == null) {
				num = 1;
				map.put(key, num);
			} else {
				map.put(key, ++num);
			}
			if (num >= 3) {
				td.businessDistrict.add(loc);
			}
		}
	}
	
	private static List<Location> duplicate(List<Location> locList, double distance) {
		if (locList.size() <= 2) {
			return locList;
		}

		Set<String> set = locList.stream().map(Location::toString).collect(Collectors.toSet());

		return set.stream()
			.map(s -> s.split(","))
			.map(ss -> new Location(Double.parseDouble(ss[0]), Double.parseDouble(ss[1])))
			.collect(Collectors.toList());
	}
	
	public String toString() {
		return userId + "\t" + pointNum + "\t" +
				home + "\t" + homeNum + "\t" +
				company + "\t" + companyNum + "\t" +
				businessDistrict + "\t" + businessDistrict.size() + "\t" + other;
	}

}
