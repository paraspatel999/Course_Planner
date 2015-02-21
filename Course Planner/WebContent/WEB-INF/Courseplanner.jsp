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
<font size="6" font-family="arial">Course Planner</font><br/><br/>
<font size="4" font-family="arial">Please select the courses you have already taken:</font><br/><br/>

<form action="courseplanner" method="post">
<table border="1">
 
<th></th><th>Code</th><th>Subject</th><th>Prerequisite</th>
<c:forEach items="${Sub_entries}" var="entry">
<tr><td><input type="checkbox" name="cp" value="${entry.getCode()}"></td><td>${entry.getCode()}</td><td>${entry.getName()}</td><td>${entry.getPrereq()}</td></tr>
</c:forEach>
</table><br/>
<input type="submit" name="Next" value="Next"/>
</form>
</body>
</html>