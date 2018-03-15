package total.service;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {
	@Autowired
	SqlSessionTemplate template;
	
	public Map<String,List<Map>>friendList(String idmail) {
		Map<String, List<Map>> list = new LinkedHashMap<>();
		list.put("myWaitingList", template.selectList("friend.myWaitingList",idmail)); // 내가 한 친신 대기 리스트
		list.put("reqToMe",template.selectList("friend.reqToMe",idmail)); // 나한테 온 친신 대기 리스트
		list.put("friends", template.selectList("friend.getList",idmail)); // 친구 리스트
		return list;
	}
	
	public List<Map> search(String idmail) {
		return template.selectList("member.searchByIdmail", "%"+idmail+"%");
	}
	
	public boolean addFriend(Map data) {
		int r = 0;
		try {
			r = template.insert("friend.addNewOne", data);
		} catch(Exception e) {
			e.printStackTrace();
		} 
			return r == 1;
	
	}
}
