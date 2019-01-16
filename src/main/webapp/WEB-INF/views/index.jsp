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
<!-- Polices Personalisées -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,700,800,900|Raleway|Sarabun"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- CSS personalisé -->
<link rel="stylesheet" href="css/general.css">
<link rel="stylesheet" href="css/index.css">
<script src="js/index.js"></script>
</head>
<body onload="displayable()">

	<jsp:include page="header.jsp"></jsp:include>

	<section class="survey-hero">
		<h1 class="my-title">Administration des sondages</h1>
		<c:if test="${ survey.id != null }">
			<div>
				<h2 class="survey-status">Sondage en cours :</h2>
				<h3 class="survey-date">Du ${survey.startDate} au
					${survey.tempEndDate}</h3>
			</div>
		</c:if>
		<c:if test="${ survey.id == null }">
			<div>
				<h2 class="survey-status">Il n'y a pas de sondage en cours.</h2>
			</div>
		</c:if>
	</section>

	<section class="button-hero">
		<div class="buttons">
			<a href="surveys.html">
				<button class="button-index liste">Liste des sondages</button>
			</a>

			<c:if test="${ survey.id != null }">
				<a href="close.html?id=${ survey.id }">
					<button class="button-index ferm">Fermer
						le sondage en cours</button>
				</a>
			</c:if>
			<c:if test="${ survey.id == null }">
				<a href="close.html?id=${ survey.id }">
					<button class="button-index ferm" disabled>Fermer le
						sondage en cours</button>
				</a>
				<c:if test="${ not empty closeMessage }" >
					<h5 style="display:none" id="displayCheck">Le sondage a bien été clos.</h5>
				</c:if>
			</c:if>



			<c:if test="${ survey.id == null }">
				<a href="form.html">
					<button class="button-index crea">Créer un nouveau sondage</button>
				</a>
			</c:if>
			<c:if test="${ survey.id !=null }">
				<a href="form.html">
					<button class="button-index crea" disabled>Créer un
						nouveau sondage</button>
				</a>
			</c:if>
		</div>
	</section>
	<section id="closeScreen">
		<div class="displayer">
			<h3>Le sondage a bien été clos.</h3>
			<button class="closeButton" onclick="closed(event)">
				<i class="material-icons">close</i>
			</button>
		</div>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>