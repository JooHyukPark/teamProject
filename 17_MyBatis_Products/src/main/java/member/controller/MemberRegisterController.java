package member.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import member.medel.MemberBean;
import member.medel.MemberDao;

@Controller
public class MemberRegisterController {
	
	@Autowired
	MemberDao mdao;
	
	private final String command = "register.mb";
	private String getPage = "memberRegisterForm";
	private String gotoPage = "redirect:/list.mb";
	
	@RequestMapping(value=command, method = RequestMethod.GET)
	public String aa() {
		
		return getPage;
	}
	
	@RequestMapping(value=command, method = RequestMethod.POST)
	public String bb(@ModelAttribute("mbean") @Valid MemberBean mbean, BindingResult result) {
		
		if(result.hasErrors()) {
			return "memberRegisterForm";
		}
		
		int cnt = mdao.memberRegister(mbean);
		
		return gotoPage;
	}
}
