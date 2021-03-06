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
<title>Cryptocurrencies List</title>
<body>
	<div id="main" class="container-fluid bg-dark">
		<div class="row justify-content-center">
			<h1 class="text-center text-uppercase text-white mt-3">my crypto currencies
			</h1>
		</div>
		<div class="jumbotron jumbotron-fluid text-center">
			<div class="row justify-content-center mb-3">
				<div class="col-2">
					<a href="crypto-add" class="btn btn-light"><i
						class="fas fa-plus-circle"></i>&nbsp;Crypto Currency</a>
				</div>
			</div>
			<div
				class="d-flex flex-wrap flex-md-nowrap justify-content-center justify-content-md-around ">
				<table class="col-12 col-md-10 text-left table table-striped bg-light mx-auto">
					<thead class="bg-secondary text-white">
						<tr>
							<th scope="col">#id</th>
							<th scope="col">Symbol</th>
							<th scope="col">Name</th>
							<th scope="col">Current Price</th>
							<th scope="col">Delta</th>
							<th scope="col">Last Update</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ cryptoCurrenciesList }" var="cryptoCurrency">
							<fmt:parseDate  value="${ cryptoCurrency.getLastUpdated() }"  type="date" pattern="yyyy-MM-dd" var="parsedDate" />
							<c:url value="/cryptocurrency-edit" var="cryptoCurrencyEdit"></c:url>
							<c:url value="/cryptocurrency-delete" var="cryptoCurrencyDelete"></c:url>
							<tr>
								<th>${ cryptoCurrency.getIdCrypto() }</th>
								<td>${ cryptoCurrency.getSymbol()}</td>
								<td><img src="${ cryptoCurrency.getImageUrl() }">&nbsp;${ cryptoCurrency.getName() }</td>
								<td>${ cryptoCurrency.getCurrentPrice() }</td>
								<c:choose>
									<c:when test="${ cryptoCurrency.getDelta() > 0 }">
										<td><i class="fas fa-arrow-up "></i></td>
									</c:when>
									<c:when test="${ cryptoCurrency.getDelta() < 0 }">
										<td><i class="fas fa-arrow-down"></i></td>
									</c:when>
									<c:otherwise>
										<td><i class="fas fa-arrow-right"></i></td>
									</c:otherwise>
								</c:choose>
									
									<td><fmt:formatDate value="${ parsedDate }" type="both" pattern="dd.MM.yyyy HH:mm:ss"/></td></td>
									<td>
										<div class="row justify-content-around">
											<a
												href="${ cryptoCurrencyEdit }?id=${ cryptoCurrency.getIdCrypto() }"><i
												class="far fa-edit btn btn-outline-success"></i></a> <a
												href="${ cryptoCurrencyDelete }?id=${ cryptoCurrency.getIdCrypto() }"><i
												class="delete fas fa-trash-alt btn btn-outline-danger" data-confirm="Are you sure to delete this item: ${cryptoCurrency.getName()} ?"></i></a>
										</div>
									</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/delete.js"></script>
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