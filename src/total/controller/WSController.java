package total.controller;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
/*
 *  Web Socket 통신을 제어할 용도의 컨트롤러 (쌍방향 통신 가능. 서버측에서 클라이언트 쪽으로 먼저 보낼 수도, 그 반대도 가능. (그 반대의 경우 ajax로도 가능))
 *  	- WebSocketHandler(interface)를 implements 해서 목적에 맞게 개조해 사용하거나
 *  	- 목적에 맞는 WebSocketHnadler를 extends 해서 사용.
 *  		- TextWebSocketHandler / BinaryWebSocketHandler (파일 데이터 주고 받을 경우)
 *    
 */
@Controller("wsController") // 이렇게 해두면 지정한 이름으로 자동 등록 됨! 안그러면 클래스명으로 등록됨...
public class WSController extends TextWebSocketHandler {
	@Autowired
	Gson gson;
	// 모든 클라이언트에게 메세지를 전송하려면 (예전에 했던 tcp 통신 방식과 비슷)
	Set<WebSocketSession> wsSessions;
	Map<String, Object> data = new HashMap<>();
	
	@PostConstruct // 빈 자동 등록시에 init-method 역할
	public void init() {
		wsSessions = new LinkedHashSet<>();
	}
	
	@Override // client측과 웹소켓 연결 됐을 때
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 이 session은 httpSession과 다른 세션임!!
		System.out.println("afterConnectionEstablished "+session.getId());
//		System.out.println(session.getRemoteAddress().getHostName()); // getHostString()이랑 둘 다 컴퓨터 이름이 나옴...
		System.out.println(session.getRemoteAddress().getAddress().getHostAddress());  // client ip
		wsSessions.add(session);
			data.put("cnt", wsSessions.size());
			data.put("info","ip "+session.getRemoteAddress().getAddress().getHostAddress()+" 님이 접속하셨습니다." );
		for(WebSocketSession ws : wsSessions) {
			ws.sendMessage(new TextMessage(gson.toJson(data)));
		}
	}
	
	 @Override // client 측에서 메세지가 들어올 때
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			System.out.println("handleTextMessage"+session + " / "+message);
	 }
	 
	 @Override // client 측과 연결 해제될 때 (refresh할 때도)
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
			System.out.println("afterConnectionClosed"+session);
			wsSessions.remove(session);
			data.put("cnt", wsSessions.size());
			data.put("info","ip "+session.getRemoteAddress().getAddress().getHostAddress()+" 님이 연결을 해제하셨습니다." );
			for(WebSocketSession ws : wsSessions) {
				ws.sendMessage(new TextMessage(gson.toJson(data)));
			}
	 }
	
}
