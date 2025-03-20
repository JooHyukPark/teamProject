package mall.cart;

import java.util.HashMap;
import java.util.Map;

public class MyCartList { // 장바구니 역할
	//한줄 더 추가
	private Map<Integer, Integer> orderlists = null;
	
	public MyCartList() {
		orderlists = new HashMap<Integer,Integer>();
	}
	
	public void addOrder(int pnum, int oqty) { // key(상품번호), value(주문수량)
		if(orderlists.containsKey(pnum) == false) {
			orderlists.put(pnum, oqty);
		}else {
			Integer value = orderlists.get(pnum);
			orderlists.put(pnum, oqty+value);
		}
	}
	
	public Map<Integer, Integer> getAllOrderLists() {
		return orderlists;
	}
}
