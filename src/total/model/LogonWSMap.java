package total.model;

import java.util.LinkedHashMap;

import org.springframework.web.socket.WebSocketSession;

public class LogonWSMap extends LinkedHashMap<String, WebSocketSession>{ 
	// spring 설정 파일에서는 generic이 안 걸리니까 아예 generic 설정한 맵 객체를 설계!
}
