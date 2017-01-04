package storm_falcon.util;

import java.util.Arrays;

public class SubPage {

	/**
	 * 计算分页页码
	 * @param page 第几页
	 * @param item 每页几条
	 * @param total 共几条
	 * @return 2个元素的数组
	 * 		0.该页第一条是总条数的第几条
	 * 		1.共几页
	 */
	public static int[] calcPage(int page, int item, int total) {
		int div = (total % item == 0) ? 0 : 1;
		int nTotalPage = total / item + div;
		int nStartNum = (page - 1) * item + 1;
		return new int[]{nStartNum, nTotalPage};
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(calcPage(3, 5, 50)));
	}
}
