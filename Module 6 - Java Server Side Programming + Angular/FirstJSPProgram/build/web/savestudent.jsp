<jsp:useBean class="model.Student" id="s"/>
<%@page import="dao.StudentDAO" %>
<jsp:setProperty name="s" property="*"/>



<%
int i= StudentDAO.saveStudent(s);

if(i>0){
    response.sendRedirect("success.jsp");
    }

else {
    
    }









%>