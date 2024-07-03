<jsp:useBean class="model.Student" id="s"/>
<%@page import="dao.StudentDao" %>
<jsp:setProperty name="s" property="*"/>

<% 

StudentDao.deleteStudent(s);
response.sendRedirect("viewallstudent.jsp");

%>