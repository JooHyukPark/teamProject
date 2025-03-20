package products.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mall.cart.ShoppingInfo;

@Component("cdao")
public class CompositeDao { // ¡∂¿Œ
	private final String namespace = "product.model.Composite";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void orderDetail() {
		sqlSessionTemplate.selectList(namespace+".orderDetail");
	}

	public List<ShoppingInfo> orderDetail(int oid) {
		// TODO Auto-generated method stub
		return null;
	}
}
