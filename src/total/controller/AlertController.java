package total.controller;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import total.model.WebSocketMap;

/*
 *		웹소켓 커넥션이 열릴 때, 세션을 key로 묶어서 브라우저 별로 관리해 보자.
 *      - HttpSession에 접근해서 세션 ID를 가져와야 함. 그게 브라우저마다 할당되므로.
 *        그러나 이 클래스에서 그냥은 접근이 안 됨. 이 클래스는 ws Protocol이고, 그건 Http Protocol 이니까!
 *      - spring 설정 파일에 웹소켓 등록할 때 HttpSessionHandshakeInterceptor 를 설정해두면
 *        이 메소드가 호출될 때, 이 클라이언트가 사용 중이던 HttpSession에 setAttribute된 값들을 WebSokcetSession에서 뽑아다 쓸수 있게 넣어줌.
 *        추가로. "HTTP.SESSION.ID" 라는 키로 사용중인 session id도 넣어주고.
 */

@Controller("alertController")
public class AlertController extends TextWebSocketHandler{
	@Autowired
	WebSocketMap sessions;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Map<String, Object> map = session.getAttributes();
		System.out.println(map); // {HTTP.SESSION.ID=BBD15BD74F6457391BFD6A51345B929C}
		
		System.out.println("AlertController_afterConnectionEstablished");
		String key = (String)map.get("HTTP.SESSION.ID");
		
		if( !sessions.containsKey(key))
			sessions.put(key, new ArrayList<>());
		sessions.get(key).add(session);
		System.out.println(sessions.size());
		for(String k : sessions.keySet()) {
			System.out.println("\t→"+k +" / "+sessions.get(k).size());
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String key = (String)session.getAttributes().get("HTTP.SESSION.ID");
		sessions.get(key).remove(session);
		if( sessions.get(key).isEmpty()) {
			sessions.remove(key);
		}
	}
}
