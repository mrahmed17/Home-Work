<%@page  import="model.Student"%>
<%@page  import="dao.StudentDao"%>


<%
String id=request.getParameter("id");

Student s=StudentDao.getById(Integer.parseInt(id));

%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="color: cyan; background-color: black">
        <h1 style="text-align: center">Student Update Form</h1>

        <form action="updatestudent.jsp" method="post" style="text-align: center">

            <input type="hidden" name="id" value="<%=s.getId()%>">

            Name: <input type="text" name="name" value="<%=s.getName()%>"> <br>
            Email: <input type="email" name="email" value="<%=s.getEmail()%>"> <br>
            Address: <input type="text" name="address" value="<%=s.getAddress()%>"> <br>
            Cell No: <input type="text" name="cell" value="<%=s.getCell()%>"> <br>

            <input type="submit" value="Save">

        </form>

        <div style="font-size: 20px; color: crimson; text-align:center;">
            <br>
            <br>
            <br>
            <br>
            <a href="home.jsp" style="color: aqua">Home</a>
            <br>
            <br>
            <a href="viewallstudent.jsp" style="color: aqua">View Student Data</a>

        </div>


    </body>
</html>
