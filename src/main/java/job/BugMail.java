package job;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Flags.Flag;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.mail.imap.IMAPFolder;

public class BugMail {

	public static String mailHost = "";
	public static String mailID = "";
	public static String mailPassword = "";
	public static String subjectText = "";
	public static String bug_Status = "";
	public static String bugKeyWord = "";
	public static String mailDateStr ="";
	public static Date mailDate;

	public void MailCheck() throws MessagingException, IOException, ParseException  {	
		ExecuteJob execute = new ExecuteJob();
		
		IMAPFolder folder = null;
		Store store = null;
		String subject = null;
		Flag flag = null;
		InputStream input = null;
		String status = "";
//		String mailDateStr;
//		Date mailDate;
		Set<String> subjectList = new HashSet<String>();
		String modules = "";
		try {
			Properties props = System.getProperties();
			Properties prop = new Properties();
			input = new FileInputStream("mail/config.properties");
			prop.load(input);
			mailHost = prop.getProperty("host");
			mailID = prop.getProperty("mailID");
			mailPassword = prop.getProperty("mailPassword");
			subjectText = prop.getProperty("subjectText");
			bug_Status = prop.getProperty("bug_Status");
			bugKeyWord = prop.getProperty("bugKeyWord");
			
		props.setProperty("mail.store.protocol", "imaps");
		
//		props.put("mail.imap.host", "outlook.office365.com");
//		props.put("mail.imap.port", "993");
//		props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		props.setProperty("mail.imap.socketFactory.fallback", "false");
//		props.setProperty("mail.imap.socketFactory.port", String.valueOf("993"));
//		
		Session session = Session.getDefaultInstance(props, null);
		
		
			  store = session.getStore("imaps");
			  store.connect(mailHost, mailID, mailPassword);
			  folder = (IMAPFolder) store.getFolder("Inbox");
			  System.out.println("No of Messages : " + folder.getMessageCount());
			  System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
			  if (!folder.isOpen())
					folder.open(Folder.READ_WRITE);
//				Message[] messages = folder.getMessages();
			  
			  
			  
			  // Fetch unseen messages from inbox folder
			  Message[] messages = folder.search(
				        new FlagTerm(new Flags(Flags.Flag.SEEN), false));
			  // Sort messages from recent to oldest
			    Arrays.sort( messages, ( m1, m2 ) -> {
			      try {
			        return m2.getSentDate().compareTo( m1.getSentDate() );
			      } catch ( MessagingException e ) {
			        throw new RuntimeException( e );
			      }
			    } );
			    
				String path = "mail/lastExecutedDate.txt";
				String canonicalPath = new File(path).getCanonicalPath();
				String contents = new String(
						Files.readAllBytes(Paths.get(canonicalPath)));
				System.out.println(contents);
//				try (PrintWriter writer = new PrintWriter("mail/lastExecutedDate.txt")) {
					Date lastExecuted = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(contents.trim());
					
			    for ( Message message : messages ) {
			    	subject =message.getSubject();
			    	String mailBody = getTextFromMessage(message);
					try {
						status = mailBody.split("Status:")[1].split("\\[")[0].trim();
						System.out.println("++++++++++++++++++" + status);
					} catch (Exception e) {

					}	
			    	if (subject.contains(subjectText) && subject.toLowerCase().contains(bugKeyWord.toLowerCase())
							&& status.toLowerCase().contains(bug_Status.toLowerCase())) {
			    		mailDateStr = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(message.getReceivedDate());
						mailDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(mailDateStr.trim());
						break;
			    	}
			    }
			    System.out.println("First message at "+ mailDate);
			    
			    
			    for ( Message message : messages ) {
			    	subject =message.getSubject();
			    	String statusText= "";
			    	String mailBody = getTextFromMessage(message);
					try {
						statusText = mailBody.split("Status:")[1].split("\\[")[0].trim();
						System.out.println("++++++++++++++++++" + statusText);
					} catch (Exception e) {

					}	
					System.out.println("Information Read "+subject +"------- "+ statusText);
			    	if (subject.contains(subjectText) && subject.toLowerCase().contains(bugKeyWord.toLowerCase())
							&& statusText.toLowerCase().contains(bug_Status.toLowerCase())) {
			    		String currentMailDateStr = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(message.getReceivedDate());
						Date currentmailDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(currentMailDateStr.trim());
						if(currentmailDate.after(lastExecuted)) {
			    	subjectList.add(subject.split("\\)")[1].trim());
			    	message.setFlag(Flag.SEEN, true);
			    	System.out.println(" subject:" + message.getSubject() );
			    	}
				    }
			    	}
			    for ( String sub:subjectList) {
			    	 modules =modules+" "+ sub;
			    }
			    System.out.println(modules);
			    	
			    
			    
			    
				
//				System.out.println(messages.length);
//				int i = messages.length - 1;
//
//				System.out.println("*****************************************************************************");
//				System.out.println("MESSAGE " + (i + 1) + ":");
//				Message msg = messages[i];
//				// System.out.println(msg.getMessageNumber());
//				// Object String;
//				// System.out.println(folder.getUID(msg)
//
//				System.out.println("Subject: " + subject);
//				System.out.println("From: " + msg.getFrom()[0]);
//				System.out.println("To: " + msg.getAllRecipients()[0]);
//				System.out.println("Date: " + msg.getReceivedDate());
//				System.out.println("Size: " + msg.getSize());
//				System.out.println(msg.getFlags());
//				// System.out.println("Body: \n"+ msg.ATTACHMENT);
//				String mailBody = getTextFromMessage(msg);
//				System.out.println("Body: \n" + mailBody);
//				String status = "";
//				try {
//					status = mailBody.split("Status:")[1].split("\\[")[0].trim();
//					System.out.println("++++++++++++++++++" + status);
//				} catch (Exception e) {
//
//				}				
			  
//				if (subject.contains(subjectText) && subject.toLowerCase().contains(bugKeyWord.toLowerCase())
//						&& status.toLowerCase().contains(bug_Status.toLowerCase())) {

//					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//					// Date d = sdf.format(obj)parse(msg.getReceivedDate().toString());
//					System.out.println("date******" + sdf.format(msg.getReceivedDate()));
//					String dir = System.getProperty("user.dir");
//					System.out.println(dir);
//					String path = "mail/lastExecutedDate.txt";
//					String canonicalPath = new File(path).getCanonicalPath();
//					String contents = new String(
//							Files.readAllBytes(Paths.get(canonicalPath)));
//					System.out.println(contents);
			    if (mailDate!=null && modules.trim()!=null) {
					try (PrintWriter writer = new PrintWriter("mail/lastExecutedDate.txt")) {
//						Date lastExecuted = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(contents.trim());
//						String mailDateStr = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(msg.getReceivedDate());
//						Date mailDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(mailDateStr.trim());
						
							String testCase_Execute = modules;
							System.out.println("Test cases to execute ---------->  "+testCase_Execute);
							System.out.println(testCase_Execute);
							execute.executeMail(testCase_Execute, "KEY");
							writer.print(mailDateStr);
							writer.close();					
					} catch (Exception e) {
						    try (PrintWriter writer = new PrintWriter("mail/lastExecutedDate.txt")) {
								writer.print(mailDateStr);
							} catch (Exception e1) {

							}}
					}
//				}
		} finally {
			if (folder != null && folder.isOpen()) {
				folder.close(true);
			}
			if (store != null) {
				store.close();
			}
			if(input != null) {
				input.close();
			}
		}}
	private String getTextFromMessage(Message message) throws MessagingException, IOException {
		String result = "";
		if (message.isMimeType("text/plain")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart);
		}
		return result;
	}
	private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();
				break; // without break same text appears twice in my tests
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
			}
		}
		return result;
	}
	}