<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="model.Student"%>
<%@page import="dao.StudentDao"%>
<%@page import="java.util.*"%>


<%
List<Student> list = StudentDao.viewAllStudent();
request.setAttribute("list", list);
%>

<jsp:include page="header.jsp" />
<div class="container text-center">

	<h1 class="text-center my-2">All Student</h1>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Address</th>
				<th>Cell</th>
				<th>Action</th>
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
					<td><a href="updatestudentform.jsp?id=${s.getId()}">Edit</a> <a
						href="deletestudent.jsp?id=${s.getId()}">Delete</a></td>
				</tr>

			</c:forEach>

		</tbody>

	</table>

</div>
<jsp:include page="footer.jsp" />
