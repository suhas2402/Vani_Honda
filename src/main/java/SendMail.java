

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SendMail")
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SendMail() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String msg = request.getParameter("msg");
		String gmail = "honda.honnali@gmail.com";
		String from = "suhasme106@gmail.com";
		String password = "vyengforcrbwhkjm";

		Properties theProperties = new Properties();

		theProperties.put("mail.smtp.auth", "true");
		theProperties.put("mail.smtp.host", "smtp.gmail.com");
		theProperties.put("mail.smtp.port", "587");
		theProperties.put("mail.smtp.starttls.required", "true");
		theProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		theProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com"); 
		Session session = Session.getInstance(theProperties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(gmail));
			message.setSubject(" New Message");
			message.setText(
					"Name: " + name + "\n Email: " + email + "\n Phone: " + phone + "\n message: " + msg);

			Transport.send(message);

	
		//	response.setContentType("text/html");  
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('Message successfully sent.. we will revert back');");  
			out.println("</script>");   
			RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
	        rd.include(request, response);  

		} catch (MessagingException e) {
			System.out.println("Error at SendingEmail.java: " + e);
			
		}
	}

}
