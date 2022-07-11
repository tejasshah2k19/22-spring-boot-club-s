<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ResetPassword</title>
</head>
<body>

	<form action="updatepassword" method="post">
		Email :<input type="text" name="email"><br> OTP :<input
			type="text" name="otp"><br> Password :<input
			type="password" name="password"><br> <br> <input
			type="submit" value="Update Password" />
	</form>
	<br> ${msg }
</body>
</html>