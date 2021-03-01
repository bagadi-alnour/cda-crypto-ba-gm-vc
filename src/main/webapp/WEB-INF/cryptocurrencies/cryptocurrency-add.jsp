<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
	integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w=="
	crossorigin="anonymous" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@600&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/list.css" />
<title>Cryptocurrency Add</title>
<body>
	<div id="main" class="container-fluid">
		<div class="row justify-content-center">
			<h1 class="text-center text-uppercase mt-3">Add a new crypto
				currency</h1>
		</div>
		<div class="jumbotron jumbotron-fluid text-center">
			<div
				class="d-flex flex-wrap flex-md-nowrap justify-content-center justify-content-md-around ">
				<c:url value="/cryptocurrency-edit" var="cryptoCurrencyEdit"></c:url>
				<form id="editForm" name="editForm" class="col-12 col-md-6"
					action="${cryptoCurrencyAdd}" method="post">
					<div class="form-group text-left">
						<label for="name">Name</label> <input class="form-control"
							type="text" id="name" name="name">
					</div>
					<div class="form-group text-left">
						<label for="symbol">Symbol</label> <input class="form-control"
							type="text" id="symbol" name="symbol">
					</div>
					<div class="form-group text-left">
						<label for="currentPrice">Current Price</label> <input
							class="form-control" type="decimal" id="currentPrice"
							name="currentPrice">
					</div>
					<div class="form-group text-left">
						<label for="imageUrl">Image Url</label> <input
							class="form-control" type="text" id="imageUrl" name="imageUrl"">
					</div>
					<div class="form-group text-left">
						<label for="lastUpdated">Last Update</label> <input
							class="form-control" type="datetime-local" id="lastUpdated"
							name="lastUpdated">
					</div>
					<input type="submit" class="btn btn-success" value="Save">
				</form>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
		integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
		crossorigin="anonymous"></script>
</body>
</html>