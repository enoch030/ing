package total.service;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
	@Autowired
	SqlSessionTemplate template;
	
	public boolean putMember(Map param) {
		int rst = template.insert("member.putMember", param);
		return rst == 1;
	}
}
