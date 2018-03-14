package total.service;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	@Autowired
	SqlSessionTemplate template;
	
	public Map loginHandle(Map<String, String> params) {
		return template.selectOne("member.getMemberByIdmailAndPass", params);
	}
	
}