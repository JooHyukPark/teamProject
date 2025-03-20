package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.medel.MemberBean;
import member.medel.MemberDao;
import utility.Paging;

@Controller
public class MemberListController {
	
	@Autowired
	MemberDao mdao;
	
	private final String command = "/list.mb";
	private String gotoPage = "memberList";
	
	@RequestMapping(command)
	public ModelAndView aa(HttpServletRequest request,
			@RequestParam(value="whatColumn", required=false) String whatColumn, 
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam(value="pageNumber", required=false) String pageNumber) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");
		
		int totalCount = mdao.getTotalCount(map);
		
		String url = request.getContextPath() + command;
		System.out.println("url : " + url);
		
		Paging pageInfo = new Paging(pageNumber,"2",totalCount,url,whatColumn,keyword);
		
		List<MemberBean> lists = mdao.getMemberList(pageInfo, map);
		System.out.println("lists.size() in controlelr: "+lists.size());
		ModelAndView mav = new ModelAndView(gotoPage);
		mav.addObject("lists",lists);
		mav.addObject("totalCount",totalCount);
		mav.addObject("pageInfo",pageInfo);
		
		return mav;
	}
}
