<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FP</title>
</head>
<body>
<h2>ForgetPassword? </h2>
	<form action="forgetpassword" method="post">
		Email :<input type="text" name="email"><br><br>
		<input type="submit" value="Send Otp"/>
	</form>
	<br>
	${msg }

</body>
</html>