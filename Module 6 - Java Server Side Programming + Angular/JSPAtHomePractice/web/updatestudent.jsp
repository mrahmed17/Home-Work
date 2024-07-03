<jsp:useBean class="model.Student" id="s"/>
<%@page  import="dao.StudentDao" %>
<jsp:setProperty name="s" property="*" />


<%
    StudentDao.updateStudent(s);

    response.sendRedirect("success.jsp");


%>