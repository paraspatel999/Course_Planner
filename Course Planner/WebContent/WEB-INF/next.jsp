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



 you may take Following Subjects in <font size="4" font-family="arial"> <u>${param.name}</u></font><br/><br/>
 <form action="next" method="post">
 <table border="1">
 
<th></th><th>Code</th><th>Subject</th><th>Prerequisite</th>
 
 
 
<c:forEach items="${Quart}" var="en">
<c:if test="${en.nextquarter eq param.name }">
<c:forEach items="${en.select }" var="entry">
<tr><td><input type="checkbox" name="cp" value="${entry.getCode()}"></td><td>${entry.getCode()}</td><td>${entry.getName()}</td><td>${entry.getPrereq()}</td></tr>
</c:forEach>
<input type="hidden" name="hid" value="${en.id}"/>
</c:if>

 </c:forEach>
 
 </table>
 
 <br/><br/>
 <input type="submit" name="next" value="Next"/>   <input type="submit" name="butt" value="Finish"/>
  </form><br/>
 



</body>
</html>