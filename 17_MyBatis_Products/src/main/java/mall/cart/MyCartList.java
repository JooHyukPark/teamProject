package mall.cart;

import java.util.HashMap;
import java.util.Map;

public class MyCartList { // ��ٱ��� ����
	//���� �� �߰�
	private Map<Integer, Integer> orderlists = null;
	
	public MyCartList() {
		orderlists = new HashMap<Integer,Integer>();
	}
	
	public void addOrder(int pnum, int oqty) { // key(��ǰ��ȣ), value(�ֹ�����)
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
