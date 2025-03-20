package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import member.medel.MemberBean;
import member.medel.MemberDao;

@Controller
public class MemberLoginController {
	
	private final String command = "/loginForm.mb";
	private String getPage = "memberLoginForm";
	private String gotoPage = "memberList";
	
	@Autowired
	MemberDao mdao;
	
	@RequestMapping(value=command, method = RequestMethod.GET)
	public String aa() {
		
		return getPage;
	}
	
	@RequestMapping(value=command, method = RequestMethod.POST)
	public ModelAndView bb(MemberBean mb, HttpSession session, HttpServletResponse response) throws IOException {
		System.out.println("mb.getId() in MemberLoginController: "+mb.getId());
		System.out.println("mb.getPassword() in MemberLoginController: "+mb.getPassword());
		
		MemberBean login = mdao.getMember(mb.getId());
		PrintWriter pw = response.getWriter();

		ModelAndView mav = new ModelAndView();
		
		
		response.setContentType("text/html:charset=UTF-8");
		if(login == null) {
			System.out.println("��ġ�ϴ� ���̵� ����.");	
			pw.println("<script type='text/javascript'>");
			pw.println("alert('��ġ�ϴ� ���̵� �����ϴ�.')");
			pw.println("</script>");
			pw.flush();
			mav.setViewName(getPage);
		}else {//��ġ�ϴ� ���̵� �ִ�.
			if(login.getPassword().equals(mb.getPassword())) {//���̵�, ��� ��� ��ġ
				session.setAttribute("loginInfo",login);
				if(session.getAttribute("destination") != null) {
					String destination = (String) session.getAttribute("destination");
					mav.setViewName(destination);
				}else {
					mav.setViewName(gotoPage);
				}
				
			}else {//���̵� ��ġ, ��� ��ġX
				pw.println("<script type='text/javascript'>");
				pw.println("alert('����� ��ġ���� �ʽ��ϴ�.')");
				pw.println("</script>");
				pw.flush();
				mav.setViewName(getPage);
			}
			
		}

		return mav;
	}
	
}
