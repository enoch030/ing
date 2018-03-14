package total.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import total.model.WebSocketMap;

@Controller
public class LogoutController {
	@Autowired
	WebSocketMap sessions;

	@RequestMapping("/logout")
	public String logoutHandle(HttpSession session) {
//		if(sessions.get(session.getId()) != null) {
//		}
		
		session.removeAttribute("logon");
		session.removeAttribute("userInfo");
		return "redirect:/index";
	}
	
}
