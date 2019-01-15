<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail du sondage</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!-- JavaScript pour JQuery, Popper, Bootstrap -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<!-- CSS personalisé -->
<link rel="stylesheet" href="css/general.css">
<link rel="stylesheet" href="css/index.css">
</head>
<body>
	<section class="surveys-hero">
		<h1>Liste des commentaires négatifs.</h1>
		<div class="table-left">
			<table>
				<tr>
					<th>Commentaire</th>
					<th>Déposé le</th>
				</tr>
				<c:forEach var="answer" items="${badAnswers}">
					<tr>
						<td>${answer.comment}</td>
						<td>${answer.entryDate}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="table-right">
			<h1>Clients qui ont laissé au moins un avis positif sur ce
				sondage</h1>
			<table>
				<tr>
					<th>Date du dépôt de l'avis</th>
					<th>Numero client</th>
					<th>Prénom</th>
					<th>Nom</th>
					<th>Email</th>
					<th>Téléphone</th>
					<th></th>
				</tr>
				<c:forEach var="answer" items="${goodAnswers}">
					<tr>
						<td>${answer.entryDate}</td>
						<td>${answer.client.clientNum}</td>
						<td>${answer.client.firstName}</td>
						<td>${answer.client.lastName}</td>
						<td>${answer.client.email}</td>
						<td>${answer.client.telNum}</td>
						<td>
							<c:if test="${empty answer.client.clientNum}">
								Nouveau client !
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</section>
</body>
</html>