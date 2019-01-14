<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detail du sondage</title>
</head>
<body>
	<section class="surveys-hero">
		<h1>Liste des commentaires négatifs.</h1>
		<div class="table-left">
			<table>
				<tr>
					<th>Commentaire</th>
				</tr>
				<c:forEach var="answer" items="${badAnswers}">
					<tr>
						<td>${answer.comment}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="table-right">
			<h1>Clients qui ont laissé au moins un avis positif sur ce
				sondage</h1>
			<table>
				<tr>
					<th>Numero client</th>
					<th>Prénom</th>
					<th>Nom</th>
					<th>Email</th>
					<th>Téléphone</th>
					<th></th>
				</tr>
				<c:forEach var="answer" items="${goodAnswers}">
					<tr>
						<td>${answer.client.clientNum}</td>
						<td>${answer.client.firstName}</td>
						<td>${answer.client.lastName}</td>
						<td>${answer.client.email}</td>
						<td>${answer.client.telNum}</td>
						<td></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</section>
</body>
</html>