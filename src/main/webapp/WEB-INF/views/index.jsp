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
<link rel="stylesheet" href="css/index.css">
</head>
<body>
	<h1>Administration des sondages</h1>
	<section class="survey-hero">
		<c:if test="${ survey.id != null }">
			<div>
				<h2>Sondage en cours :</h2>
				<h3>Du ${survey.startDate} au ${survey.tempEndDate}</h3>
				<h2></h2>
			</div>
		</c:if>
		<c:if test="${ survey.id == null }">
			<div>
				<h2>Il n'y a pas de sondages en cours.</h2>
			</div>
		</c:if>
	</section>
	<section class="button-hero">
		<a href="surveys.html">
			<button class="button">Liste des sondages</button>
		</a> <a href="close.html?id=${ survey.id }">
			<button class="button">Fermer le sondage en cours</button> <c:if
				test="${ not empty closeMessage }">
				<h5>Le sondage a bien été clos.</h5>
			</c:if>
		</a>
		<c:if test="${ survey.id == null }">
			<a href="form.html">
				<button class="button">Créer un nouveau sondage</button>
			</a>
		</c:if>
		<c:if test="${ survey.id !=null }">
			<a href="form.html">
				<button class="button" disabled>Créer un nouveau sondage</button>
			</a>
		</c:if>
	</section>
	<footer>
		<div class="footer">
			<p></p>
		</div>
	</footer>
</body>
</html>