package products.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import products.model.ProductsBean;
import products.model.ProductsDao;
import utility.Paging;

@Controller
public class ProductListController {
	
	@Autowired
	ProductsDao pdao;
	private final String command = "/list.prd";
	private String getPage = "ProductList";
	
	@RequestMapping(command)
	public ModelAndView aa(HttpServletRequest request) {
		String whatColumn = request.getParameter("whatColumn");
		String keyword = request.getParameter("keyword");
		String pageNumber = request.getParameter("pageNumber");
		
		System.out.println("what Column in controller: "+ whatColumn);
		System.out.println("keyword in controller: "+ keyword);
		Map<String, String> map = new HashMap<String,String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");
		
		int totalCount = pdao.getTotalCount(map);
		System.out.println("total Count in Controller: "+totalCount);
		String url = request.getContextPath()+command;
		
		Paging pageInfo = new Paging(pageNumber,"2",totalCount,url,whatColumn,keyword);
		
		List<ProductsBean> lists = pdao.selectAllProducts(map, pageInfo);
		System.out.println("lists.size() in controller: "+lists.size());
		ModelAndView mav = new ModelAndView(getPage);
		mav.addObject("lists", lists);
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("totalCount", totalCount);
		
		return mav;
	}
}