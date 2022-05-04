<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %> 

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>LIST_ARTIClES</title>
</head>
<body> 


	<form action="c" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<input type="file" name="file" accept="application/pdf" multiple /><br> 
			<input type="submit" value="upload" /><br><br>
	   </div>	
	</form>



	<c:if test="${LIST != null}">

		<table  class="table table-hover  table-responsive table-sm">
			<thead>
				<tr>
					<th scope="col"> Name</th>
					<th scope="col">Price</th>
				</tr>
			</thead>
	</c:if>
			<c:forEach var="tempArticle1" items="${LIST}">
				<tr>
					<td>${tempArticle1.name}</td>
					<td>${tempArticle1.price}</td>
				</tr>

			</c:forEach>

		</table>


		<c:if test="${CHECK_FILE}">
		<div class="alert alert-danger" role="alert alert-danger ">
			<H2>This is not REWE_BONE PDF file ${FOULT_FILE}</H2>
	   </div>
		</c:if>
	
</body>
</html>