<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display courses</title>
</head>
<body>
<font size="6" font-family="arial">Course Planner</font><br/><br/>
<table border="1">
<c:choose>
  <c:when test="${empty sessionScope.user}">
  <div>
   <a href="login" ><b>Login</b></a>&nbsp&nbsp<a href="register"><b>New Registration</b></a>
   </div><br>
    <a href="courseplanner">Course Planner</a><br/><br/>
  </c:when>
  <c:otherwise>
  <div><a href="courseplanner" >Course Planner</a>&nbsp&nbsp<a href="logout" ><b>Logout</b></a></div><br/>
  <a href="user_plan">Saved course plans</a>
  <br/><br/>
  </c:otherwise>
  </c:choose>
 
<th>Code</th><th>Subject</th><th>Prerequisite</th><th>Operation</th>
<c:forEach items="${Sub_entries}" var="entry">
<tr><td>${entry.getCode()}</td><td>${entry.getName()}</td><td>${entry.getPrereq()}</td><td><a href="edit?id=${entry.getId()}" >edit</a></td></tr>
</c:forEach>
</table><br/>
<a href="add">ADD Subject</a>


</body>
</html>