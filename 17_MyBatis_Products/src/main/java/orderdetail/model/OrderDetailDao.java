package orderdetail.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("oddao")
public class OrderDetailDao {

	private String namespace = "orderdetail.model.OrderDetail";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int insertOrderDetail(OrderDetailBean odBean){
		System.out.println("odBean.getOid() in insertOrderDetail(): "+odBean.getOid());
		System.out.println("odBean.getPnum() in insertOrderDetail(): "+odBean.getPnum());
		int cnt = sqlSessionTemplate.insert(namespace+".insertOrderDetail",odBean);
		System.out.println("cnt in insertOrderDetail(): "+cnt);
		return cnt;
	}
}
