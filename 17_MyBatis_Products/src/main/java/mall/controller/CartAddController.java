package mall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mall.cart.MyCartList;

@Controller
public class CartAddController {
	private final String command = "add.mall";// productsDetailForm.jsp���� ��û
	private String gotoPage = "redirect:/list.mall";
	
	@RequestMapping(value=command, method = RequestMethod.POST)
	public String aa(@RequestParam(value = "orderqty",required = false) Integer orderqty,
					@RequestParam(value = "num", required = false) Integer num,
					HttpSession session) {
		
		if(session.getAttribute("loginInfo")==null) {
			session.setAttribute("destination", "redirect:/list.prd");
			return "redirect:/loginForm.mb";
		}else {
			MyCartList mycart = (MyCartList) session.getAttribute("mycart");
			if(mycart == null) {
				mycart = new MyCartList(); 
			}
			session.setAttribute("mycart", mycart);
			
			mycart.addOrder(num, orderqty);
			return gotoPage;
			//kim �߰���
			//master�� ������
			//kim�� ������
			//kim �߰���
			//kim �߰���
		}
		
	}
	
}
