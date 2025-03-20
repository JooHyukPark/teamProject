package products.controller;


import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.multi.MultiMenuItemUI;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import products.model.ProductsBean;
import products.model.ProductsDao;


@Controller
public class ProductDeleteController {
	
	private final String command = "/delete.prd";
	private String gotoPage = "redirect:/list.prd";

	@Autowired
	ProductsDao pdao;	
	
	//자동으로 만들어지는 객체라 주입시키기만 하면 됨.
	@Autowired
	ServletContext servletContext;
	//이친구 JSP에서 활용할 때 application이란 이름의 객체로 활용했던 것.
	
	@RequestMapping(value=command)
	public String bb(HttpServletRequest request, Model model) {

		ProductsBean pbean = pdao.getOneByNum(request.getParameter("num")); // DB 테이블 수정
		int cnt = pdao.deleteProduct(request.getParameter("num")); // DB 테이블 수정
		System.out.println("cnt in controller: "+cnt);
		
		model.addAttribute("pageNumber", request.getParameter("pageNumber"));
		model.addAttribute("keyword", request.getParameter("keyword"));
		model.addAttribute("whatColumn", request.getParameter("whatColumn"));

		String uploadPath = servletContext.getRealPath("/resources/uploadImage/");
		File url = new File(uploadPath);
		File destination = new File(url, pbean.getImage()); //기존이미지 삭제할 경로
		
		if(cnt != -1) {
			try {
				destination.delete();//웹서버 폴더에서 기존 이미지 삭제
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return gotoPage;
	}
}
