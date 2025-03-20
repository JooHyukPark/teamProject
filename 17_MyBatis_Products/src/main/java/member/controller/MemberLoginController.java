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
			System.out.println("일치하는 아이디가 없다.");	
			pw.println("<script type='text/javascript'>");
			pw.println("alert('일치하는 아이디가 없습니다.')");
			pw.println("</script>");
			pw.flush();
			mav.setViewName(getPage);
		}else {//일치하는 아이디가 있다.
			if(login.getPassword().equals(mb.getPassword())) {//아이디, 비번 모두 일치
				session.setAttribute("loginInfo",login);
				if(session.getAttribute("destination") != null) {
					String destination = (String) session.getAttribute("destination");
					mav.setViewName(destination);
				}else {
					mav.setViewName(gotoPage);
				}
				
			}else {//아이디 일치, 비번 일치X
				pw.println("<script type='text/javascript'>");
				pw.println("alert('비번이 일치하지 않습니다.')");
				pw.println("</script>");
				pw.flush();
				mav.setViewName(getPage);
			}
			
		}

		return mav;
	}
	
}
