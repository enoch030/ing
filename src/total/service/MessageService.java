package total.service;

import java.io.IOException;
import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;

@Service
public class MessageService  {
	@Autowired
	Gson gson;
	
	Map<String, List<WebSocketSession>> sessions;
	
	@PostConstruct
	public void init() {
		sessions = new LinkedHashMap<>();
	}
	
	public void addWebSocket(String id, WebSocketSession session) {
		if(! sessions.containsKey(id) )
			sessions.put(id, new ArrayList<>());
		sessions.get(id).add(session);
		System.out.println(id+"의 웹소켓 세션");
		for(WebSocketSession s : sessions.get(id)) {
			System.out.println("\t→"+s.getId());
		}
	}
	
	public void sendRequestMessage(String one, String other) throws IOException {
		if(sessions.containsKey(other)) { // 친추 대상자가 로그온 중이라면
			Map data = new LinkedHashMap<>();
				data.put("mode", "fr_req");
				data.put("from", one);
			for(WebSocketSession ws : sessions.get(other)) { // 친추 대상자의 모든 세션에 메세지 보내기
				ws.sendMessage(new TextMessage(gson.toJson(data)));
			}
		}
	}
}
