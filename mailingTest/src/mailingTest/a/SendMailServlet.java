package mailingTest.a;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

@WebServlet("/SendMailServlet.do")
public class SendMailServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String senderName = req.getParameter("senderName");
		String senderEmail = req.getParameter("senderEmail");
//		String senderPwd = req.getParameter("");
		String receiverName = req.getParameter("receiverName");
		String receiverEmail = req.getParameter("receiverEmail");
		String emailSubject = req.getParameter("emailSubject");
		String emailContent = req.getParameter("emailContent");
		System.out.println(emailContent);
		mailing(senderName, senderEmail, receiverName, receiverEmail, emailSubject, emailContent);
	}

	private void mailing(String senderName, String senderEmail, String receiverName, String receiverEmail,
			String emailSubject ,String emailContent) {
		
<<<<<<< Updated upstream
		SimpleEmail email = new SimpleEmail();
		email.setCharset("UTF-8");
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("jjgod66gi66", "uwxjutcvuatdsvhq"));
		email.setSSL(true);
		try {
			email.addTo(receiverEmail, receiverName);
		} catch (EmailException e) {
			e.printStackTrace();
			System.out.println("수신실패");
		}
		try {
			email.setFrom(senderEmail, senderName);
		} catch (EmailException e) {
			e.printStackTrace();
			System.out.println("발신실패");
		}
		email.setSubject(emailSubject);
		email.setContent(emailContent, "text/html; charset=UTF-8");
		try {
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
			System.out.println("전송x");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
=======
			Mailing(mappingInfo(req));
		
		
		if (successMail) {
			RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
			rd.forward(req, res);
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("resultFail.jsp");
			rd.forward(req, res);
		}
	}
	
	private Map<String, String> mappingInfo(HttpServletRequest req) {
		// form에서 받은 정보들을 map에 담는다
		Map<String, String> mailInfo = new HashMap<>();
		
		mailInfo.put("senderName", req.getParameter("senderName"));
		mailInfo.put("senderId", req.getParameter("senderId"));
		mailInfo.put("domain", req.getParameter("domain"));
		mailInfo.put("senderPwd", req.getParameter("senderPwd"));
		mailInfo.put("receiverName", req.getParameter("receiverName"));
		mailInfo.put("receiverEmail", req.getParameter("receiverEmail"));
		mailInfo.put("emailSubject", req.getParameter("emailSubject"));
		mailInfo.put("mailSet", req.getParameter("mailSet"));
		mailInfo.put("emailContent", req.getParameter("emailContent"));
		return mailInfo;
	}
	
	private void Mailing(Map<String, String> mailInfo) {
		// map에서 정보들을 꺼내 메일을 보낸다
		String senderName = mailInfo.get("senderName");
		String senderId = mailInfo.get("senderId");
		String domain = mailInfo.get("domain");
		String senderPwd = mailInfo.get("senderPwd");
		String receiverName = mailInfo.get("receiverName");
		String receiverEmail = mailInfo.get("receiverEmail");
		String emailSubject = mailInfo.get("emailSubject");
		String mailSet = mailInfo.get("mailSet");
		String emailContent = mailInfo.get("emailContent");
		String senderEmail = senderId +  "@" + domain;
		
		// text형식으로 보낼 때
		if (mailSet.equals("text")) {
			SimpleEmail email = new SimpleEmail();
			email.setCharset("UTF-8");
			email.setHostName("smtp.".concat(domain));
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(senderId, senderPwd));
			email.setSSLOnConnect(true);
			try {
				email.addTo(receiverEmail, receiverName);
			} catch (EmailException e) {
				e.printStackTrace();
				System.out.println("수신실패");
			}
			try {
				email.setFrom(senderEmail, senderName);
			} catch (EmailException e) {
				e.printStackTrace();
				System.out.println("발신실패");
			}
			email.setSubject(emailSubject);
			email.setContent(emailContent, "text/plain; charset=UTF-8");
			
			try {
				email.send();
				successMail = true;
			} catch (EmailException e) {
				e.printStackTrace();
				System.out.println("전송에러");
			}
			
		// html 형식으로 보낼 때	
		} else if (mailSet.equals("html")) {
			HtmlEmail email = new HtmlEmail();
			email.setCharset("UTF-8");
			email.setHostName("smtp.".concat(domain));
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(senderId, senderPwd));
			email.setSSLOnConnect(true);
			try {
				email.addTo(receiverEmail, receiverName);
			} catch (EmailException e) {
				e.printStackTrace();
				System.out.println("수신실패");
			}
			try {
				email.setFrom(senderEmail, senderName);
			} catch (EmailException e) {
				e.printStackTrace();
				System.out.println("발신실패");
			}
			email.setSubject(emailSubject);
			try {
				email.setMsg(emailContent);
			} catch (EmailException e1) {
				e1.printStackTrace();
			}
			try {
				email.send();
				successMail = true;
			} catch (EmailException e) {
				e.printStackTrace();
				System.out.println("전송에러");
			}
		}
>>>>>>> Stashed changes
	}

}


