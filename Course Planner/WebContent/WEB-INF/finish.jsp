<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Planner</title>
</head>
<body>
<c:choose>
<c:when test="${not empty param.error}">

<b>You have already completed all subjects.</b>
</c:when>
<c:otherwise>
<font size="4" font-family="arial">The courses that you have already taken in previous quarters:</font><br/><br/>


<c:choose>
 <c:when test="${not empty already}">
 <table border="1">
<th>Code</th><th>Subject</th><th>Prerequisite</th>

<c:forEach items="${already}" var="entry">
<tr></td><td>${entry.getCode()}</td><td>${entry.getName()}</td><td>${entry.getPrereq()}</td></tr>
</c:forEach>
</table>
 </c:when>
 <c:otherwise>
 You have not taken any courses in previous quarters.
</c:otherwise>

</c:choose>
<br/><br/>
 <font size="4" font-family="arial">Here is your course plan:</font><br/><br/>
 
<c:forEach items="${Quart}" var="en">
<c:choose>
<c:when test="${not empty en.taken}">
<font sixe="2" font-family=" arial"><b><u>${en.nextquarter}</u></b></font><br/><br/>
<table border="1">
 
<th>Code</th><th>Subject</th><th>Prerequisite</th>

<c:forEach items="${en.taken}" var="entry">
<tr></td><td>${entry.getCode()}</td><td>${entry.getName()}</td><td>${entry.getPrereq()}</td></tr>
</c:forEach>

</table>
</c:when>
<c:otherwise>
<font sixe="2" font-family=" arial"><b><u>${en.nextquarter}</u></b></font><br/><br/>
<b>You have not selected any subject for this quarter.</b>

</c:otherwise>
</c:choose>
<br/>
<br/>

 </c:forEach>
 </c:otherwise>
</c:choose>
 
 <br/><br/>
 <c:choose>
 <c:when test="${empty sessionScope.user}">
 <a href="Display">Done</a>
 </c:when>
 <c:otherwise>
 <a href="Display">Done</a>   |  <a href="saved_plan">Save your plan</a>
 </c:otherwise>
 </c:choose>

 
 
 
 
 
</body>
</html>