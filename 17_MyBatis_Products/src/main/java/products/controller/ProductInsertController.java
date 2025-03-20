package products.controller;


import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import products.model.ProductsBean;
import products.model.ProductsDao;


@Controller
public class ProductInsertController {
	
	private final String command = "/insert.prd";
	private String getPage = "ProductInsertForm";
	private String gotoPage = "redirect:/list.prd";

	@Autowired
	ProductsDao pdao;	
	
	//자동으로 만들어지는 객체라 주입시키기만 하면 됨.
	@Autowired
	ServletContext servletContext;
	//이친구 JSP에서 활용할 때 application이란 이름의 객체로 활용했던 것.
	
	@RequestMapping(value=command, method = RequestMethod.GET)
	public String aa(HttpServletRequest request, HttpSession session) {
		System.out.println("loginInfo in ProductInsertController: "+session.getAttribute("loginInfo"));
		
		if(session.getAttribute("loginInfo")==null) {//로그인 안했으면
			session.setAttribute("destination", "redirect:/insert.prd");
			return "redirect:/loginForm.mb";
		}else { //로그인했으면
			return getPage;
		}

	}
	
	@RequestMapping(value=command, method = RequestMethod.POST)
	public String bb(@ModelAttribute("pbean") @Valid ProductsBean pbean, BindingResult result, 
					HttpServletRequest request) {
		
		System.out.println("pbean.getImage() in controller: "+pbean.getImage());
		
		if(result.hasErrors()) {
			return getPage;
		}
		
		String uploadPath = servletContext.getRealPath("/resources/uploadImage/");
		System.out.println("uploadPath(): " + uploadPath);
		
		File url = new File(uploadPath);
		url.mkdirs();//이경로로 폴더 만들어라
		
		//a경로에 b이미지 올리겠다는 선언 이게 매우 흥미롭네.
		File destination = new File(url, pbean.getImage());
		
		//실제 파일 추가 시작
		MultipartFile multi = pbean.getUpload();		
		
		int cnt = pdao.insertProduct(pbean); // db products insert
		System.out.println("cnt in controller: "+cnt);
		if(cnt != -1) {
			try {
				multi.transferTo(destination); // 해당 폴더에 이미지 업로드
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			return gotoPage;
		}else {
			return getPage;
		}
		
	}
}
