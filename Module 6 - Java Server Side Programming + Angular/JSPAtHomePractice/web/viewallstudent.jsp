<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page  import="model.Student"%>
<%@page  import="dao.StudentDao"%>
<%@page  import="java.util.*"%>


<%
    List<Student>list=StudentDao.viewAllStudent();

    request.setAttribute("list", list);
    
%>

<body style="color: cyan; background-color: black">
    <table border="1" style="width:70%; font-size: 20px; margin-left: auto; margin-right: auto">
        <thead>
            <tr>
                <th>ID </th>
                <th>Name </th>
                <th>Email </th>
                <th>Address </th>
                <th>Cell </th>
                <th>Action Button</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach var="s" items="${list}">
                <tr>
                    <td>${s.getId()}</td>
                    <td>${s.getName()}</td>
                    <td>${s.getEmail()}</td>
                    <td>${s.getAddress()}</td>
                    <td>${s.getCell()}</td>
                    <td>
                        <a href="updatestudentform.jsp?id=${s.getId()}" style="color: aqua"> Edit </a> 
                        <a href="deletestudent.jsp?id=${s.getId()}" style="color: aqua"> Delete </a>
                    </td>
                </tr>
            </c:forEach>

        </tbody>

    </table>

    <div style="font-size: 20px; color: crimson; text-align:center; ">

        <br>
        <br>
        <br>
        <br>
        <a href="home.jsp" style="color: aqua">Home</a> <br>
        <a href="studentaddform.jsp" style="color: aqua">Fillup Student Form</a> <br>

    </div>

</body>