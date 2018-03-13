package total.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewService {
	@Autowired
	SqlSessionTemplate template;

	public boolean getLvByIdmail(String idmail) {
		Integer lv = template.selectOne("member.getLvByIdmail", idmail);
		return lv == 1;
	}
}
