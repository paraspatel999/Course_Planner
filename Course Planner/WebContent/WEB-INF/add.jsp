<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add courses</title>
</head>
<body>
	<c:if test="${empty sessionScope.user}">
		<c:redirect url="login" />
	</c:if>
	<form action="add" method="post">
		<table border="1">
			<tr>
				<td>Code</td>
				<td><input type="text" name="code" /></td>
			</tr>
			<tr>
                <td>Title</td>
                <td><input type="text" name="title"/></td>
			</tr>
			<tr>
			     <td>Prerequisite(s):</td>
			     <td>
			     <c:forEach items="${Sub_entries}" var="entry">
			      <input type='checkbox' name='add_sub' value="${entry.getCode()}"/>${entry.getCode()}<br>
			     </c:forEach>
			     </td>
			     
			</tr>
			<tr>
			    <td colspan="2"><input type="submit" value="add" name="add"></td>
			</tr>
		</table>
	</form>
<br><br><a href="logout">Logout</a>
</body>
</html>