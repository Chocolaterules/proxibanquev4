<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Entête d'ajout de librairie de balises Spring pour les formulaires. -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administration des sondages</title>
<!-- CSS de Bootstrap -->
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
<link rel="stylesheet" href="css/surveys.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<section class="surveys-hero">
		<div class="subheader">
			<h1 class="my-title">Liste des sondages réalisés</h1>
			<div class="my-icons">
				<a href="index.html">
					<i class="material-icons">home</i>
				</a>
			</div>
		</div>
		<div class="maTable">
			<table>
				<tr>
					<th>Sondage du :</th>
					<th>Au :</th>
					<th>Réponses positives</th>
					<th>réponses négatives</th>
					<th>Lien</th>
				</tr>
				<c:forEach var="survey" items="${surveys}">
					<tr>
						<td>${survey.startDate}</td>
						<td><c:out
								value="${not empty survey.endDate ? survey.endDate : 'Sondage en cours'}"></c:out>
						</td>
						<td>${survey.positiveCount}</td>
						<td>${survey.negativeCount}</td>
						<td><a href="surveyDetail.html?id=${survey.id}"><i
								class="material-icons"> call_made </i></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>