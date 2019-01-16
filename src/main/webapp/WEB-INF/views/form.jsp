<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Entête d'ajout de librairie de balises Spring pour les formulaires. -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouveau sondage</title>
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
<link rel="stylesheet" href="css/form.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<section class="form-hero">
		<div class="subheader">
			<h1 class="my-title">Créer un nouveau sondage</h1>
			<div class="my-icons">
				<a href="index.html">
					<i class="material-icons">home</i>
				</a>
			</div>
		</div>
		<div class="my-form">
			<form method="post" action="form.html">
				<div class="start-date">
					<label for="startDate">Date de début :</label>
					<input class="my-input" id="startDate" name="startDate" type="date"/>
				</div>
				<div class="end-date">
					<label for="tempEndDate">Date de fin :</label>
					<input class="my-input" id="tempEndDate" name="tempEndDate" type="date"/>
				</div>
				<div class="my-buttons">
					<button class="button" type="submit"><i class="material-icons" style="font-size: 3em;">check</i></button>
					<button class="button" type="reset"><i class="material-icons" style="font-size: 3em;">refresh</i></button>
				</div>
			</form>
		</div>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>