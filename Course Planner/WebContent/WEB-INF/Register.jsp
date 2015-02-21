<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>




<form action="register" method="post">
<table>
<tr><td>Username:</td><td> <input type="text" name="user_name" /></td><td>

   <c:choose>
         <c:when test="${m.get('uname_empty') ne null  }">
           
             <font color="red">${m.get("uname_empty")}.</font>
          </c:when>
          <c:otherwise>
               <c:choose>
                   <c:when test="${m.get('uname_short') ne null }">
                      <font color="red">${m.get("uname_short")}.</font>
                   </c:when>
                 <c:otherwise>
           <c:choose>
                    <c:when test="${m.get('user_exist') ne null }">
                    <font color="red">${m.get("user_exist")}.</font>
                   </c:when>
         </c:choose>
            </c:otherwise>
           </c:choose>
          </c:otherwise>
          
        
       
   </c:choose>


</td>
</tr><tr><td>Password:</td><td> <input type="password" name="password" /></td><td>
<c:choose>
   <c:when test="${m.get('pass_empty') ne null}">
       <font color="red">${m.get("pass_empty")}</font>
   </c:when>
   <c:otherwise>
        <c:choose>
           <c:when test="${m.get('pass_short') ne null}">
               <font color="red">${m.get("pass_short")}</font>
           </c:when>
   </c:choose>
   </c:otherwise>
</c:choose>
</tr>
<tr><td>Re-type password:</td><td> <input type='password' name='re_password' /></td><td>
<c:choose>
    <c:when test="${m.get('pass_match') ne null}">
   <font color="red">${m.get("pass_match")}</font>
   </c:when>
</c:choose>
</td>
<tr><td>First_name:(Optional)</td><td> <input type='text' name='f_name' /></td></tr>
<tr><td>Last_name:(Optional)</td><td> <input type="text" name="l_name" /></td></tr>
<tr><td></td><td><input type="submit" name="register" value="Register" /></td></tr> 
</table></form>

</body>
</html>