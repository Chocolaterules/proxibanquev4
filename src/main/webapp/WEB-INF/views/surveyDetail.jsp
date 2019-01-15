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
<link rel="stylesheet" href="css/surveyDetail.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="subheader">
		<div>
			<h1 class="my-title">Détail du sondage</h1>
		</div>
		<div class="my-icons">
			<a href="surveys.html">
				<i class="material-icons">arrow_back_ios</i>
			</a>
			<a href="index.html">
				<i class="material-icons">home</i>
			</a>
		</div>
	</div>
	<section class="surveys-hero">
		<div class="table-container">
			<div class="table-left">
				<h5>Liste des avis positifs</h5>
				<table>
					<tr>
						<th>Date</th>
						<th>Id Client</th>
						<th>Prénom</th>
						<th>Nom</th>
						<th>E-mail</th>
						<th>Téléphone</th>
						<th style="width: auto;"></th>
					</tr>
					<c:forEach var="answer" items="${goodAnswers}">
						<tr>
							<td>${answer.entryDate}</td>
							<td>${answer.client.clientNum}</td>
							<td>${answer.client.firstName}</td>
							<td>${answer.client.lastName}</td>
							<td>${answer.client.email}</td>
							<td>${answer.client.telNum}</td>
							<td style="background-color: #F7F7F7"><c:if
									test="${empty answer.client.clientNum}">
									<i class="material-icons"> person_add </i>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="table-right">
				<h5>Liste des avis négatifs</h5>
				<table>
					<tr>
						<th style="width: 35em;">Commentaire</th>
						<th>Date</th>
					</tr>
					<c:forEach var="answer" items="${badAnswers}">
						<tr>
							<td>${answer.comment}</td>
							<td>${answer.entryDate}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>