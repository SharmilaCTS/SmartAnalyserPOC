package job;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class StoryMail {

	public static String mailHost = "";
	public static String mailID = "";
	public static String mailPassword = "";
	public static String subjectText = "";
	public static String story_Status = "";
	public static String storyKeyWord = "";

	public void MailCheck(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		PrintWriter out=res.getWriter();
		out.println("Checking for Stories in Mail");
}}
