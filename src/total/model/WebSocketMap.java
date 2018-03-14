package total.model;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.socket.WebSocketSession;

public class WebSocketMap extends LinkedHashMap<String, List<WebSocketSession>> { // generic 걸린 전용 맵객체를 아예 생성

}
