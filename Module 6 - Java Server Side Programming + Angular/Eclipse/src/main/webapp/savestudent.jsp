<jsp:useBean class="model.Student" id="e" />

<%@page import="dao.StudentDao"%>

<jsp:setProperty name="e" property="*" />


<%
StudentDao.saveStudent(e);

response.sendRedirect("success.jsp");

response.sendRedirect("error.jsp");
%>