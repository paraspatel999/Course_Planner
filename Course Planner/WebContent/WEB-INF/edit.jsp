<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Course</title>
</head>
<body>

    
    <c:set var="id1" value="${param.id}"/>
    <form action="edit?id=${id1 }" method="post">
    <table border="1">
    <tr><td>Code</td>
    <td><input type="text" name="code" value="${d.getCode()}"/></td></tr><tr><td>Title</td><td><input type="text" name="title" value="${d.getName() }"/></td></tr>
    <tr><td>Prerequisite(s):</td><td>
    
    <c:forEach items="${Sub_entries}" var="entry">
      <c:if test="${entry.getId()!=id1}">
         <c:choose>
             <c:when test="${p.contains(entry.code) && p.size()!=0}">
              <input type="checkbox" name="edit_sub" value="${entry.getCode()}" checked="checked"/>${entry.getCode()}<br/>
         </c:when>
         <c:otherwise>
         <input type="checkbox" name="edit_sub" value="${entry.getCode()}"/> ${entry.getCode()}<br/>
         </c:otherwise>
         </c:choose>
   
         
      </c:if>
    </c:forEach>
    </td></tr>
    <tr><td colspan="2"><input type="submit" name="save" value="save" /></td></tr>
   
    </table>
    </form>
    <br/><br><a href="logout">Logout</a>
</body>
</html>