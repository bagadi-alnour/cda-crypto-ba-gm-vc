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
<title>Wallets List</title>
<body>
	<div id="main" class="container-fluid bg-dark">
		<div class="row justify-content-center">
			<h1 class="text-center text-uppercase text-white mt-3">my wallets</h1>
		</div>
		<div class="jumbotron jumbotron-fluid text-center">
			<div class="row justify-content-center mb-3">
				<div class="col-2">
					<a href="wallet-add" class="btn btn-light"><i
						class="fas fa-plus-circle"></i>&nbsp;Crypto Wallet</a>
				</div>
			</div>

			<div
				class="d-flex flex-wrap flex-md-nowrap justify-content-center justify-content-md-around ">
				<table class="col-md-10 text-left table table-striped bg-light mx-auto">
					<thead class="bg-secondary text-white">
						<tr>
							<th scope="col">#idWallet</th>
							<th scope="col">#idCrypto</th>
							<th scope="col">Purchase Price</th>
							<th scope="col">Quantity</th>
							<th scope="col">Purchase Date</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ cryptoWalletsList }" var="wallet">
							<fmt:parseDate  value="${ wallet.getPurchaseDate() }"  type="date" pattern="yyyy-MM-dd" var="parsedDate" />
							<c:url value="/wallet-edit" var="cryptoWalletEdit"></c:url>
							<c:url value="/wallet-delete" var="cryptoWalletDelete"></c:url>
							<tr>
								<th>${ wallet.getIdWallet() }</th>
								<td>${ wallet.getIdCrypto() }</td>
								<td>${ wallet.getPurchasePrice() }</td>
								<td>${ wallet.getQuantity() }</td>
								<td><fmt:formatDate value="${ parsedDate }" type="both" pattern="dd.MM.yyyy HH:mm:ss"/></td>
								<td>
									<div class="row justify-content-around">
										<a href="${ cryptoWalletEdit }?id=${ wallet.getIdWallet() }"><i
											class="far fa-edit btn btn-outline-success"></i></a>
											
										<a href="${ cryptoWalletDelete }?id=${ wallet.getIdWallet() }"><i
											class="delete fas fa-trash-alt btn btn-outline-danger" data-confirm="Are you sure to delete this item: ${wallet} }?"></i></a>
											
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