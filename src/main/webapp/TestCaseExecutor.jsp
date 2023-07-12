<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.text.SimpleDateFormat"%>

<%@ page import="java.util.Date"%>

<%@ page import="java.util.Calendar"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Smart Portal</title>



<style>
html {
	min-height: 100%;
}

body {
	background: -webkit-linear-gradient(#93B874, #C9DCB9);
	background: -o-linear-gradient(#93B874, #C9DCB9);
	background: -moz-linear-gradient(#93B874, #C9DCB9);
	background: linear-gradient(#93B874, #C9DCB9);
	background-color: #93B874;
}
</style>

</head>

<body>



	<center>

		<H1>SMART PORTAL - CK</H1>

	</center>

	<form action="JobProcessor" method="post">

		<div style="margin: 0 auto; max-width: 500px;">
			<fieldset style="border: 2px solid #008000; width: 500px">

				<legend>
					<INPUT TYPE="RADIO" NAME="jobType" VALUE="instantjob" Checked
						onclick="test(this);"> INSTANT JOB
				</legend>
				<table width="100%" border="0" BORDERCOLOR=RED cellspacing="5"
					cellpadding="2">

					<tr>
						<td>
							<%-- <%

            String checkboxState ="";

            if(!request.getParameter("jobType").equalsIgnoreCase("instantjob")){

                  boolean checkboxDisabled = true; //do your logic here

                  checkboxState = checkboxDisabled ? "disabled" : "";

            }

 

%> --%> <script type="text/javascript">
	function test(obj) {

		if (document.getElementsByName("jobType")[0].checked == true) {

			document.getElementsByName("instantJob")[0].disabled = false;

			document.getElementsByName("instantJob")[1].disabled = false;

			/*  document.getElementsByName("instantJob")[2].disabled = false;  */

		} else {

			document.getElementsByName("instantJob")[0].disabled = true;

			document.getElementsByName("instantJob")[1].disabled = true;

			/*  document.getElementsByName("instantJob")[2].disabled = true; */

		}

	}

	/*    else document.getElementsByName("aForm[0].info")[0].checked = false;{

	 document.getElementsByName("aForm[0].gender")[0].enabled = true;
	 */
</script> &nbsp;&nbsp;&nbsp;&nbsp; <INPUT TYPE="CHECKBOX" NAME="instantJob"
							VALUE="bug"> Impacted Test Run

							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

							 <a href="Report.jsp">Test Run Reports</a> <br>


						</td>
					</tr>
				</table>
			</fieldset>
			<br> <br>
			<!-- <fieldset style="border: 2px solid #008000; width: 500px"> -->
			<table width="70%" border="0" cellspacing="2" cellpadding="2">

				<tr>
					<td>&nbsp;&nbsp;&nbsp;<INPUT TYPE="RADIO" NAME="jobType"
						VALUE="regression" onclick="test(this);"> COMPLETE TEST
					</td>
				</tr>
			</table>
			
			<table width="70%" border="0" cellspacing="2" cellpadding="2">

				<tr>
					<td>&nbsp;&nbsp;&nbsp;<INPUT TYPE="RADIO" NAME="jobType"
						VALUE="regression" onclick="test(this);"> SMOKE TEST
					</td>
				</tr>
			</table>
			<!-- </fieldset> -->

			<br> <br>
			<fieldset style="border: 2px solid #008000; width: 500px">
				<legend>
					<INPUT TYPE="RADIO" NAME="jobType" VALUE="batchjob"
						onclick="test(this);"> BATCH JOB
				</legend>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">

					<tr>

						<td>
							<%-- <INPUT type="text" maxlength="9" size="" name="fromDate" value="<%= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new java.util.Date()) %>"> --%>
							&nbsp;&nbsp;&nbsp;&nbsp; FROM <INPUT type="text" maxlength="10"
							size="8" name="fromDate"
							value="<%=new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date())%>">

							<%
								Calendar now = Calendar.getInstance();

								now.set(Calendar.HOUR_OF_DAY, 18);

								now.set(Calendar.MINUTE, 0);

								now.set(Calendar.SECOND, 0);
							%> <INPUT type="text" maxlength="5" size="5" name="fromTime"
							value="<%=new SimpleDateFormat("HH:mm:ss").format(now.getTime())%>">

							TO <%
 	Calendar to = Calendar.getInstance();

 	to.add(Calendar.DAY_OF_YEAR, 1);

 	to.set(Calendar.HOUR_OF_DAY, 9);

 	to.set(Calendar.MINUTE, 0);

 	to.set(Calendar.SECOND, 0);
 %> <INPUT type="text" maxlength="10" size="8" name="toDate"
							value="<%=new SimpleDateFormat("dd/MM/yyyy").format(to.getTime())%>">

							<INPUT type="text" maxlength="5" size="5" name="toTime"
							value="<%=new SimpleDateFormat("hh:mm:ss").format(to.getTime())%>">


							<br> <INPUT type="hidden" name="from"
							value="<%=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(now.getTime())%>">

							<INPUT type="hidden" name="to"
							value="<%=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(to.getTime())%>">
						</td>

					</tr>

				</table>
			</fieldset>

			<br>



		</div>

		<center>

			<input type="submit" value="Submit" />

		</center>

	</form>



</body>

</html>

