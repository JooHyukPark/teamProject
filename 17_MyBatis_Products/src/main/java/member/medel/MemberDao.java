package member.medel;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("mdao")
public class MemberDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	String nameSpace = "member.model.Member.";

	public MemberBean getMember(String id) {
		MemberBean login = null;
		login = sqlSessionTemplate.selectOne(nameSpace+"getMember",id);
		System.out.println("login in MemberDao: "+login);
		return login;
	}

	public int memberRegister(MemberBean mbean) {
		int cnt = sqlSessionTemplate.insert(nameSpace+"memberRegister",mbean);
		return cnt;
	}

	public List<MemberBean> getMemberList(Paging pageInfo, Map<String, String> map) {
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		List<MemberBean> lists = sqlSessionTemplate.selectList(nameSpace+"getMemberList",map,rowBounds);
		System.out.println("lists.size() in dao: "+lists.size());
		return lists;
	}

	public int getTotalCount(Map<String, String> map) {
		int totalCount = sqlSessionTemplate.selectOne(nameSpace+"getTotalCount", map);
		return totalCount;
	}

	public int updateMpoint(String memberId, int i) {
		MemberBean mb = new MemberBean();
		mb.setId(memberId);
		mb.setMpoint(i);
		int cnt = sqlSessionTemplate.update(nameSpace+"updateMpoint",mb);
		return cnt;
	}
	
	
}
