package com.cda.jee.crypto.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cda.jee.crypto.model.CryptoCurrency;

@WebServlet("/cryptocurrencies")
public class CryptoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		List<CryptoCurrency> cryptocurrenciesList = new ArrayList<>();
		CryptoCurrency crypto = new CryptoCurrency(1, "Bitcoin", "btc", 1203.12, "https://assets.coingecko.com/coins/images/1/thumb/bitcoin.png?1547033579", LocalDateTime.of(2017, 2, 13, 15, 56));
		CryptoCurrency crypto02 = new CryptoCurrency(2, "Ethereum", "eth", 568.18, "https://assets.coingecko.com/coins/images/279/thumb/ethereum.png?1595348880", LocalDateTime.of(2017, 2, 13, 15, 56));
		CryptoCurrency crypto03 = new CryptoCurrency(3, "Cardano", "ada", 4687.46, "https://assets.coingecko.com/coins/images/975/thumb/cardano.png?1547034860", LocalDateTime.of(2017, 2, 13, 15, 56));
		CryptoCurrency crypto04 = new CryptoCurrency(4, "Tether", "usdt", 693.54, "https://assets.coingecko.com/coins/images/325/thumb/Tether-logo.png?1598003707", LocalDateTime.of(2017, 2, 13, 15, 56));
		CryptoCurrency crypto05 = new CryptoCurrency(5, "Binance Coin", "bnb", 720.89, "https://assets.coingecko.com/coins/images/825/thumb/binance-coin-logo.png?1547034615", LocalDateTime.of(2017, 2, 13, 15, 56));
		CryptoCurrency crypto06 = new CryptoCurrency(6, "Polkadot", "dot", 200.03, "https://assets.coingecko.com/coins/images/12171/thumb/aJGBjJFU_400x400.jpg?1597804776", LocalDateTime.of(2017, 2, 13, 15, 56));
		cryptocurrenciesList.add(crypto);
		cryptocurrenciesList.add(crypto02);
		cryptocurrenciesList.add(crypto03);
		cryptocurrenciesList.add(crypto04);
		cryptocurrenciesList.add(crypto05);
		cryptocurrenciesList.add(crypto06);
		req.setAttribute("cryptoCurrenciesList", cryptocurrenciesList);
        this.getServletContext().getRequestDispatcher("/WEB-INF/cryptocurrencies/list.jsp").forward(req, resp);
	}
}
