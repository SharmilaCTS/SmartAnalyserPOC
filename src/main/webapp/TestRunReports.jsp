<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.io.IOException"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bug - Impacted Modules Executed Testcase status</title>
</head>
<body>


	<%
		String sourceDirectory = "mail/TestRunReports";
		File f = new File(sourceDirectory);
		File[] fileObjects = f.listFiles();
	%>
	<table>
		<tr>
			<td><h3>files</h3></td>
			<td><h3>last Modified</h3></td>
		</tr>
		<%
			for (int i = 0; i < fileObjects.length; i++) {
				System.out.println(fileObjects[i].getAbsolutePath());
				if (!fileObjects[i].isDirectory()) {
		%>
		<tr>
			<td><A
				HREF="<%=request.getContextPath()%>/callme2?filename=<%=fileObjects[i].getName()%>&report=bug"><%=fileObjects[i].getName()%></A>
				&nbsp;&nbsp;&nbsp;&nbsp; (<%=Long.toString(fileObjects[i].length())%>
				bytes long)</td>
			<td><%=new SimpleDateFormat("dd-MM-yyyy HH-mm-ss")
							.format(new Date(fileObjects[i].lastModified()))%></td>
		</tr>
		<%
			}
			}
		%>
	</table>

</body>
</html>