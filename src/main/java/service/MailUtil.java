package service;


import java.io.File;  
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;  
import org.springframework.mail.javamail.JavaMailSenderImpl;  
import org.springframework.mail.javamail.MimeMessageHelper;  

/** 
*  
* @author geloin 
* @date 2012-5-8 ����11:02:41 
*/  
public class MailUtil {  
	 public static final String HOST = "mail.gdufs.edu.cn";
	// public static final String HOST = "smtp.sina.com";
	    public static final String PROTOCOL = "smtp";   
	    public static final int PORT = 25;
	    //public static final int PORT = 587;
	    public static final String FROM = "20131003694@gdufs.edu.cn";//�����˵�email
	    public static final String PWD = "111946";//����������
	    //public static final String FROM = "helenhuang33@sina.com";
	   // public static final String PWD = "8683946333";
	    //public static final String PWD = "aeergiphsxicbfca";
	    /**
	     * ��ȡSession
	     * @return
	     */
	    private static Session getSession() {
	        Properties props = new Properties();
	        props.put("mail.smtp.host", HOST);//���÷�������ַ
	        props.put("mail.store.protocol" , PROTOCOL);//����Э��
	        props.put("mail.smtp.port", PORT);//���ö˿�
	        props.put("mail.smtp.auth" , "true");
	        props.put("mail.debug", "true");  
	       // props.put("mail,stmp.credentials", "fff123456");
	        Authenticator authenticator = new Authenticator() {
	 
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(FROM, PWD);
	            }
	             
	        };
	        Session session = Session.getDefaultInstance(props , authenticator);
	         
	        return session;
	    }
	     
	    public static void send(String toEmail , String content) {
	        Session session = getSession();
	        try {
	            System.out.println("--send--"+content);
	            // Instantiate a message
	            Message msg = new MimeMessage(session);
	  
	            //Set message attributes
	            msg.setFrom(new InternetAddress(FROM));
	            InternetAddress[] address = {new InternetAddress(toEmail)};
	            msg.setRecipients(Message.RecipientType.TO, address);
	            msg.setSubject("HD Bank");
	            msg.setSentDate(new Date());
	            msg.setContent(content , "text/html;charset=utf-8");
	  
	            //Send the message
	            Transport.send(msg);
	        }
	        catch (MessagingException mex) {
	            mex.printStackTrace();
	        }
	    }
}