package job;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 * Servlet implementation class JobProcessor
 */
@WebServlet("/JobProcessor")
public class JobProcessor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static boolean jobExecute = false;
	public static String jobType = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JobProcessor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void init(FilterConfig filterConfig) throws ServletException { }
	
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		BugMail bug = new BugMail();
		StoryMail story = new StoryMail();
		Date now = new Date();
		jobType = request.getParameterValues("jobType")[0];
		if (jobType.contains("instantjob")) {
			jobExecute = true;
//			PrintWriter writer = new PrintWriter("mail/lastExecutedDate.txt");
//			writer.print(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(now).toString());
//			writer.close();
			String selectedJobs[] = request.getParameterValues("instantJob");
			if (selectedJobs != null) {
				System.out.println(selectedJobs.length);

				List<String> jobs = Arrays.asList(selectedJobs);

				/*
				 * if (jobs.contains("bug")) { try { bug.MailCheck(); } catch
				 * (MessagingException e) { // TODO Auto-generated catch block
				 * e.printStackTrace(); } catch (ParseException e) { // TODO Auto-generated
				 * catch block e.printStackTrace(); } }
				 */
				while (jobExecute) {
					if (!jobType.contains("instantjob"))
						jobExecute = false;
					if (jobs.contains("bug")) {

						try {
							bug.MailCheck();
							jobExecute = false;
						} catch (MessagingException | ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					if (jobs.contains("story")) {

					}
				}

			}
		}
		if (jobType.contains("regression")) {

			ExecuteJob execute = new ExecuteJob();
			execute.executeMail("", "bug");
			request.getRequestDispatcher("webapp/TestCaseExecutor.jsp").forward(request, response);
		}

		if (jobType.contains("batchjob")) {
			jobExecute = true;
//			BugMail_Batch batch = new BugMail_Batch();
			String fromDate = (String) request.getParameter("fromDate")+" "+(String) request.getParameter("fromTime");
			String toDate = (String) request.getParameter("toDate")+" "+(String) request.getParameter("toTime");

			try {
				Date from = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(fromDate.trim());
				Date to = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(toDate.trim());

				if (now.after(from) && now.after(to)) {
//					batch.MailCheck(from, to);
				}
				if (now.before(from) && now.before(to)) {
					PrintWriter writer = new PrintWriter("mail/lastExecutedDate.txt");
					writer.print(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(now).toString());
					writer.close();

					while ((new Date()).before(to)) {
						try {
							bug.MailCheck();
//							story.MailCheck();
						} catch (MessagingException | ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

				if (now.after(from) && now.before(to)) {
//					batch.MailCheck(from, to);
					
					PrintWriter writer = new PrintWriter("mail/lastExecutedDate.txt");
					writer.print(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(now).toString());
					writer.close();

					while ((new Date()).before(to)) {

						try {
							bug.MailCheck();
//							story.MailCheck();
						} catch (MessagingException | ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}

				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// batch.MailCheck("","bug");

		}

		 doGet(request, response);
	}


}
