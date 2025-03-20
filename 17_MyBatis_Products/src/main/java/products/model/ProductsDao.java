package products.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("pdao")
public class ProductsDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	String nameSpace = "product.model.Product";
	
	public List<ProductsBean> selectAllProducts(Map<String, String> map, Paging pageInfo) {
		System.out.println("map whatColumn in dao: "+map.get("whatColumn"));
		System.out.println("map keyword in dao: "+map.get("keyword"));
		RowBounds rowbounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		
		List<ProductsBean> lists = sqlSessionTemplate.selectList(nameSpace+".selectAllProducts",map,rowbounds);
		System.out.println("lists.size() in dao: "+lists.size());
		
		return lists;
	}

	public int getTotalCount(Map<String, String> map) {
		int totalCount = sqlSessionTemplate.selectOne(nameSpace+".getTotalCount",map);
		return totalCount;
	}

	public int insertProduct(ProductsBean pbean) {
		System.out.println("pbean.image in dao: "+pbean.getImage());
		int cnt = -1;
		cnt = sqlSessionTemplate.insert(nameSpace+".insertProduct",pbean);
		System.out.println("cnt in dao: " + cnt);
		return cnt;
	}

	public ProductsBean getOneByNum(String num) {
		System.out.println("num in dao: "+num);
		ProductsBean pbean = sqlSessionTemplate.selectOne(nameSpace+".getOneByNum",num);
		System.out.println("pbean.getName() in dao: "+pbean.getName());
		return pbean;
	}

	public int updateProduct(ProductsBean pbean) {
		System.out.println("pbean.image in dao: "+pbean.getImage());
		int cnt = -1;
		cnt = sqlSessionTemplate.update(nameSpace+".updateProduct",pbean);
		System.out.println("cnt in dao: " + cnt);
		return cnt;
	}

	public int deleteProduct(String num) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(nameSpace+".deleteProduct",num);
		System.out.println("cnt in dao: " + cnt);
		return cnt;
	}
	
	//재고 수량 감소
	public int updateStock(int num, int qty) {
		System.out.println("num in ProductsDao: "+num);
		System.out.println("qty in ProductsDao: "+qty);
		
		ProductsBean pb = new ProductsBean();
		pb.setNum(num);
		pb.setStock(""+qty);
		
		int cnt = sqlSessionTemplate.update(nameSpace+".updateStock",pb);
		return cnt;
	}

}
