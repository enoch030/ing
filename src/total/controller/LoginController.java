package total.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import total.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	LoginService loginService;

	@RequestMapping(method = RequestMethod.GET)
	public String loginHandle() {
		return "login";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST)
	public String loginPostHandle(Map map, @RequestParam Map params, HttpSession session) {
		Map user = loginService.loginHandle(params);
		if (user != null) {
			session.setAttribute("userInfo", user);
			session.setAttribute("logon", user.get("ID"));
			return "index";
		} else {
			map.put("msg", "없는 아이디거나 아이디/비밀번호가 일치하지 않습니다.");
			System.out.println("로그인 실패");
			return "login";
		}
	}

}