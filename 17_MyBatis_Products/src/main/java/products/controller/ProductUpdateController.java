package products.controller;


import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
public class ProductUpdateController {
	
	private final String command = "/update.prd";
	private String getPage = "ProductUpdateForm";
	private String gotoPage = "redirect:/list.prd";

	@Autowired
	ProductsDao pdao;	
	
	@Autowired
	HttpSession session;
	
	//자동으로 만들어지는 객체라 주입시키기만 하면 됨.
	@Autowired
	ServletContext servletContext;
	//이친구 JSP에서 활용할 때 application이란 이름의 객체로 활용했던 것.
	
	@RequestMapping(value=command, method = RequestMethod.GET)
	public ModelAndView aa(
			@RequestParam("num") String num,
			@RequestParam(value="pageNumber", required=false) String pageNumber,
			@RequestParam(value="whatColumn", required=false) String whatColumn,
			@RequestParam(value="keyword", required=false) String keyword
			) {
		ModelAndView mav = new ModelAndView(getPage);
		
		if(session.getAttribute("loginInfo")==null) {
			session.setAttribute("destination", "redirect:/update.prd?num="+num);
			mav.setViewName("redirect:/loginForm.mb");
			mav.addObject("pageNumber",pageNumber);
			mav.addObject("whatColumn",whatColumn);
			mav.addObject("keyword",keyword);
			return mav;
		}else {			
			ProductsBean pb = pdao.getOneByNum(num);
			mav.addObject("pbean", pb);
			mav.setViewName(getPage);
			return mav;
		}
		
	}
	
	@RequestMapping(value=command, method = RequestMethod.POST)
	public String bb(@ModelAttribute("pbean") @Valid ProductsBean pbean, BindingResult result, 
					HttpServletRequest request, Model model) {
		
		System.out.println("pbean.getImage() in controller: "+pbean.getImage());
		
		if(pbean.getImage().equals("")) {
			pbean.setImage(pbean.getUpload2());
		}
		
		
		if(result.hasErrors()) {
			return getPage;
		}
		
		String uploadPath = servletContext.getRealPath("/resources/uploadImage/");
		File url = new File(uploadPath);
		File destination = new File(url, pbean.getImage()); //새이미지 업로드할 경로
		File destination2 = new File(url, pbean.getUpload2()); //기존이미지 삭제할 경로
		
		model.addAttribute("pageNumber", request.getParameter("pageNumber"));
		model.addAttribute("keyword", request.getParameter("keyword"));
		model.addAttribute("whatColumn", request.getParameter("whatColumn"));
		
		
		int cnt = pdao.updateProduct(pbean); // DB 테이블 수정
		System.out.println("cnt in controller: "+cnt);
		if(cnt != -1) {
			try {
				destination2.delete();//웹서버 폴더에서 기존 이미지 삭제
				//실제 파일 추가 시작
				MultipartFile multi = pbean.getUpload();
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
