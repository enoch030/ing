package total.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import total.service.FriendService;
import total.service.MessageService;

@Controller
@RequestMapping("/friend")
public class FriendController {
	@Autowired
	FriendService friendService;
	@Autowired
	Gson gson;
	@Autowired
	MessageService messageService;

	@RequestMapping(method = RequestMethod.GET)
	public String friendListHandle(HttpSession session, Map<String, Object> map) {
		String idmail = (String) session.getAttribute("logon");
		map.put("friendList", friendService.friendList(idmail));
		return "friend";
	}

	@RequestMapping(path = "/search", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String searchUserHandle(@RequestParam String idmail) {
		List<Map> userList = friendService.search(idmail);
		return gson.toJson(userList);
	}

	@RequestMapping("/add")
	public String addFriendHandle(@RequestParam String id, HttpSession session, Map map) {
		String one = (String) session.getAttribute("logon");
		Map data = new HashMap();
		data.put("one", one);
		data.put("other", id);
		boolean result = friendService.addFriend(data);
		if (result) {
			try {
				map.put("alert", id + "님에게 친구 신청 했습니다.");
				messageService.sendRequestMessage(one, id);
			} catch (IOException e) {
				map.put("alert", "친구 신청 실패");
				friendListHandle(session, map);
			}
		} else {
			map.put("alert", "친구 신청 실패");
		}
		friendListHandle(session, map);
		String idmail = (String) session.getAttribute("logon");
		map.put("friendList", friendService.friendList(idmail));
		return "friend";
	}

	public void cancleHandle(@RequestParam String no) {
		
	}
}
