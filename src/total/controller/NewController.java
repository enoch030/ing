package total.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import total.service.NewService;

@Controller
public class NewController {
	@Autowired
	NewService newService;
	
	@RequestMapping("/new")
	public String newHandle(HttpSession session) {
		if( newService.getLvByIdmail((String)session.getAttribute("logon")) ) {
			return "new";
		} else {
			return "redirect:unverified-email";
		}
	}
	
	@RequestMapping("/unverified-email")
	public String unverifiedEmailHandle() {
		return "unverified-email";
	}
	
}
