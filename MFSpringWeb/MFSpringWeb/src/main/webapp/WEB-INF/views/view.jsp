<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
	<title>Spring 4 MVC Hello World Example with Maven Eclipse</title>
	<link rel='stylesheet' href='<c:url value="/resources/css/style.css" />' type='text/css' media='all' /> 
</head>
<body>
Provided user id is ${user.id}<br>
Provided user name is ${user.name}<br>
Provided user name is ${user.userName}<br>
Provided user pass is ${user.password}<br>

<form:form action="4/profileForm" method="post" enctype="multipart/form-data">
<div class="form-group">
<label for="txtUserName">Choose File</label>
<input type="file" name="profileImage"/>
</div>
<button type="submit" class="btn btn-
success">Upload</button>
<a href="${user.id}" class="btn btn-primary">Cancel</a>
</form:form>


</body>
</html>
