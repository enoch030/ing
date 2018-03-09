package total.controller;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import total.service.JoinService;

@Controller
@RequestMapping("/join")
public class JoinController {
	@Autowired
	JoinService joinService;

	@RequestMapping(method=RequestMethod.GET)
	public String join01Handle() {
		return "join";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String join02Handle(HttpSession session, @RequestParam Map param, ModelMap modelMap) {
		
		try {
		if(joinService.putMember(param) ) {
			session.setAttribute("logon", param.get("id"));
			return "redirect:/";
		} 
		throw new Exception();
		
		} catch (Exception e) {
			modelMap.put("error", "계정 생성에 문제가 발생하였습니다. 정보 확인 후, 다시 시도해 주세요.");
			modelMap.put("id", param.get("id"));
			modelMap.put("email", param.get("email"));
			return "join";
		}
	}
	
}
