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
	
	//�ڵ����� ��������� ��ü�� ���Խ�Ű�⸸ �ϸ� ��.
	@Autowired
	ServletContext servletContext;
	//��ģ�� JSP���� Ȱ���� �� application�̶� �̸��� ��ü�� Ȱ���ߴ� ��.
	
	@RequestMapping(value=command)
	public String bb(HttpServletRequest request, Model model) {

		ProductsBean pbean = pdao.getOneByNum(request.getParameter("num")); // DB ���̺� ����
		int cnt = pdao.deleteProduct(request.getParameter("num")); // DB ���̺� ����
		System.out.println("cnt in controller: "+cnt);
		
		model.addAttribute("pageNumber", request.getParameter("pageNumber"));
		model.addAttribute("keyword", request.getParameter("keyword"));
		model.addAttribute("whatColumn", request.getParameter("whatColumn"));

		String uploadPath = servletContext.getRealPath("/resources/uploadImage/");
		File url = new File(uploadPath);
		File destination = new File(url, pbean.getImage()); //�����̹��� ������ ���
		
		if(cnt != -1) {
			try {
				destination.delete();//������ �������� ���� �̹��� ����
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return gotoPage;
	}
}
