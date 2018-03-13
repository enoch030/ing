package total.service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
	JavaMailSender mailSender;
	
	public boolean sendWelcomMail(String target) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			message.setRecipient(RecipientType.TO, new InternetAddress(target)); // 수신자
			message.setFrom(new InternetAddress("administrator@spring.io")); // 송신자 - google 서버는 이 설정 무시함ㅋ
			message.setSubject("[SpringIO] 가입을 축하드립니다."); // 메일 제목
			
			String content = "가입을 축하드립니다. <br>사용에 불편한 점 있다면 언제든 고객센터로 연락 주세요.<br>"; // 내용
//				content += "\n<a href=\"http:\\192.168.10.95\?emailAuth?key=\">사이트 바로가기</a>";
			message.setContent(content, "text/html;charset=utf-8"); // html 짜서 html 문서로 보낼 수도 있음! 단, String으로....허허...
																	// text/plain;charset=utf-8
			mailSender.send(message); // 발송
			return true;
		} catch(MessagingException e) { // 사용자 이메일 주소가 없으면 터지는 에러
			e.printStackTrace();
			return false;
		}
	}
	
}
