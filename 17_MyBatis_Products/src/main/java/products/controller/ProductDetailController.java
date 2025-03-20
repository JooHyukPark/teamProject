package products.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import products.model.ProductsBean;
import products.model.ProductsDao;
import utility.Paging;

@Controller
public class ProductDetailController {
	
	@Autowired
	ProductsDao pdao;
	private final String command = "/detail.prd";
	private String getPage = "ProductDatail";
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(command)
	public ModelAndView aa(@RequestParam("num") String num, @RequestParam("pageNumber") String pageNumber) {
		System.out.println("num in controller: "+num);
		System.out.println("pageNumber in controller: "+pageNumber);
		
		ProductsBean pb = pdao.getOneByNum(num);
		System.out.println("pbean.getName() in controller: "+pb.getName());
		
		String uploadPath = servletContext.getRealPath("/resources/uploadImage/");
		System.out.println("uploadPath() in detail controller: " + uploadPath);
		
		ModelAndView mav = new ModelAndView(getPage);
		
		mav.addObject("uploadPath", uploadPath);
		mav.addObject("pb", pb);
		return mav;
	}
}
