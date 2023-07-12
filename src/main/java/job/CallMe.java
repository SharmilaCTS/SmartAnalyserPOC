package job;



import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class callme
 */
@WebServlet("/CallMe")
public class CallMe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CallMe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream input = null;
		OutputStream output = null;
		String path = "java -jar mail/jenkins-cli.jar -s http://" + "localhost" + ":" + "8080/" + " -auth " + "admin" + ":" + "admin" + " build " + "SmartAnalyserTests/allure";
		Process process = Runtime.getRuntime().exec(path);
		System.out.println(process);
//		try {
			
			
//		}
			// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

//			response.setContentType("text/html");
//			System.out.println("Welcome");
//			String filename = (String) request.getParameter("filename");
//			String report = (String) request.getParameter("report");
//			String filePath = "";
//			String path = "";
//			String canonicalPath = "";
//
//			if (report.contains("stories"))
//				filePath = "C:\\Reports\\";
//			if (report.contains("bug"))
//				filePath = "C:\\TestRunReports\\";
//			path = filePath + filename;
//			canonicalPath = new File(path).getCanonicalPath();
//			input = new FileInputStream(canonicalPath);
//			output = response.getOutputStream();
//			byte[] buf = new byte[4096];
//			int len = -1;
//
//			while ((len = input.read(buf)) != -1) {
//				output.write(buf, 0, len);
//			}
//		}
//		finally {
//			if(input != null) {
//				input.close();
//			}
//			if(output != null) {
//				output.flush();
//				output.close();
//			}
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
