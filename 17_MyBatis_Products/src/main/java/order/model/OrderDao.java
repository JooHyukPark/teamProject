package order.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("odao")
public class OrderDao {

	private String namespace = "order.model.Order";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int insertOrder(String memberId) {
		System.out.println("memberId in dao: " + memberId);
		int cnt = sqlSessionTemplate.insert(namespace+".insertOrder",memberId);
		System.out.println("cnt in dao: "+cnt);
		return cnt;
	}

	public int getMaxOid() {
		int moid = sqlSessionTemplate.selectOne(namespace+".getMaxOid");
		return moid;
	}

	public List<OrderBean> getOrders(String id) {
		System.out.println("id in getOrders: " + id);
		List<OrderBean> olist = sqlSessionTemplate.selectList(namespace+".getOrders",id);
		System.out.println("olist.size() in dao: "+olist.size());
		return olist;
	}
}
