<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
	<title>Spring 4 MVC Hello World Example with Maven Eclipse</title>
	<link rel='stylesheet' href='<c:url value="/resources/css/style.css" />' type='text/css' media='all' /> 
</head>
<body>
	<h2>List all users</h2>
	<p>Number of users: ${all-users-count}</p>
	<p>Welcome, ${all-users}</p>
	<BR>
	<!-- <c:forEach var="user" items="${all-users}" >
  		${user}
	</c:forEach> -->
</body>
</html>
