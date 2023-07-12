package job;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.awt.Desktop;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetReport extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		 
	         //making a desktop object
	         Desktop desktop = Desktop.getDesktop();
	         try {
	            URI uri = new URI("file:///C:/ProgramData/Jenkins/.jenkins/workspace/SmartAnalyserTests/target/surefire-reports/emailable-report.html");
	            desktop.browse(uri);
	         } catch (IOException excp) {
	            excp.printStackTrace();
	         } catch (URISyntaxException excp) {
	            excp.printStackTrace();
	         }
	      }
	   
		}


