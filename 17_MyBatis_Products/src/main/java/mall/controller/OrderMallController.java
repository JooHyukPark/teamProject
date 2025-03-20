package mall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import member.medel.MemberBean;
import order.model.OrderBean;
import order.model.OrderDao;

@Controller
public class OrderMallController {

	private final String command = "/order.mall"; // start.jsp
	private String getPage = "myShoppingList";
	
	@Autowired
	private OrderDao orderDao;
	
	@RequestMapping(command)
	public String aa(HttpSession session, Model model) {
		MemberBean mb = (MemberBean)session.getAttribute("loginInfo");
		
		if(session.getAttribute("loginInfo") == null) {
			session.setAttribute("destination", "redirect:/order.mall");
			return "redirect:/loginForm.mb";
		}else {

			OrderDao odao = new OrderDao();
			List<OrderBean> olist = odao.getOrders(mb.getId());
			System.out.println("olist.size() in controller: "+olist.size());
			model.addAttribute(olist);
			return getPage;
		}
	}
}
