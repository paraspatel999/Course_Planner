<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Saved Plans</title>
</head>
<body>
<c:choose>
<c:when test="${not empty param.dis}">
<c:forEach items="${Plans}" var="plan" varStatus="status">
<a href="user_plan?id=${Ids[status.index]}">Saved at ${plan}</a><br/>
</c:forEach>

<br/><br/>
<a href="Display">Back</a>
</c:when>
<c:otherwise>

 

 <font size="4" font-family="arial">Here is your course plan:</font><br/><br/>
 
<c:forEach items="${Quart}" var="en">
<c:choose>
<c:when test="${not empty en.taken}">
<font sixe="2" font-family=" arial"><b><u>${en.nquarter}</u></b></font><br/><br/>
<table border="1">
 
<th>Code</th><th>Subject</th><th>Prerequisite</th>

<c:forEach items="${en.taken}" var="entry">
<tr></td><td>${entry.getCode()}</td><td>${entry.getName()}</td><td>${entry.getPrereq()}</td></tr>
</c:forEach>

</table>
</c:when>
<c:otherwise>
<font sixe="2" font-family=" arial"><b><u>${en.nquarter}</u></b></font><br/><br/>
<b>You have not selected any subject for this quarter.</b>

</c:otherwise>
</c:choose>
<br/>
<br/>

 </c:forEach>
 <a href="user_plan">Back</a>
 </c:otherwise>
</c:choose>
 
 <br/><br/>

 
 
</body>
</html>