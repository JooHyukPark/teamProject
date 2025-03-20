package mall.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mall.cart.MyCartList;
import member.medel.MemberBean;
import member.medel.MemberDao;
import order.model.OrderDao;
import orderdetail.model.OrderDetailBean;
import orderdetail.model.OrderDetailDao;
import products.model.ProductsDao;

@Controller
public class CalculateController {
	
	//수정 test
	
	private final String command = "calculate.mall"; // mallList.jsp 결제하기 클릭
	private final String gotoPage = "redirect:/list.prd"; 
	
	@Autowired
	private MemberDao mdao;
	
	@Autowired
	private ProductsDao pdao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@RequestMapping(value=command)
	public String doAction(HttpSession session) {
		MyCartList mycart = (MyCartList)session.getAttribute("mycart");
		Map<Integer,Integer> orderlists = mycart.getAllOrderLists();
		
		System.out.println(orderlists.toString());
		
		MemberBean loginInfo = (MemberBean) session.getAttribute("loginInfo"); // 설정
		String memberId = loginInfo.getId();
		System.out.println("id: " + memberId);
		
		int cnt = orderDao.insertOrder(memberId);
		System.out.println("cnt in controller: " + cnt);
		
		int maxOid = orderDao.getMaxOid();
		System.out.println("maxOid: " + maxOid);
		
		for(Integer pnum : orderlists.keySet()) {
			Integer qty = orderlists.get(pnum);
			OrderDetailBean odBean = new OrderDetailBean();
			
			odBean.setOid(maxOid);
			odBean.setPnum(pnum);
			odBean.setQty(qty);
			int cnt2 = orderDetailDao.insertOrderDetail(odBean);
			System.out.println("cnt2 from insertOrderDetail() in controller: " + cnt2);
		
			int cnt3 = pdao.updateStock(pnum, qty);
			System.out.println("cnt3 from updateStock() in controlelr: "+cnt3);
		}
		
		int cnt4 = mdao.updateMpoint(memberId,100);
		session.removeAttribute("mycart");
		
		return gotoPage;
	}
	
	
}
