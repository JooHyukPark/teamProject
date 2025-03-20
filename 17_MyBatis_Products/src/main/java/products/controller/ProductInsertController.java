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
	
	//�ڵ����� ��������� ��ü�� ���Խ�Ű�⸸ �ϸ� ��.
	@Autowired
	ServletContext servletContext;
	//��ģ�� JSP���� Ȱ���� �� application�̶� �̸��� ��ü�� Ȱ���ߴ� ��.
	
	@RequestMapping(value=command, method = RequestMethod.GET)
	public String aa(HttpServletRequest request, HttpSession session) {
		System.out.println("loginInfo in ProductInsertController: "+session.getAttribute("loginInfo"));
		
		if(session.getAttribute("loginInfo")==null) {//�α��� ��������
			session.setAttribute("destination", "redirect:/insert.prd");
			return "redirect:/loginForm.mb";
		}else { //�α���������
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
		url.mkdirs();//�̰�η� ���� ������
		
		//a��ο� b�̹��� �ø��ڴٴ� ���� �̰� �ſ� ��̷ӳ�.
		File destination = new File(url, pbean.getImage());
		
		//���� ���� �߰� ����
		MultipartFile multi = pbean.getUpload();		
		
		int cnt = pdao.insertProduct(pbean); // db products insert
		System.out.println("cnt in controller: "+cnt);
		if(cnt != -1) {
			try {
				multi.transferTo(destination); // �ش� ������ �̹��� ���ε�
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
