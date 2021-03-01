package com.cda.jee.crypto.controllers.crypto;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cda.jee.crypto.dao.impl.CryptoCurrencyImpl;
import com.cda.jee.crypto.dao.impl.CryptoWalletImpl;
import com.cda.jee.crypto.model.CryptoCurrency;
import com.cda.jee.crypto.model.CryptoWallet;

@WebServlet("/cryptocurrencies")
public class CryptoList extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		CryptoCurrencyImpl cryptoCurrenciesImpl = new CryptoCurrencyImpl();
		List<CryptoCurrency> cryptoCurrenciesList = cryptoCurrenciesImpl.getAll();
		CryptoWalletImpl cryptoWalletImpl = new CryptoWalletImpl();
		List<CryptoWallet> cryptoWalletsList = cryptoWalletImpl.getAll();	
		double delta;
		for (CryptoCurrency cryptoCurrency : cryptoCurrenciesList) {
			for (CryptoWallet wallet : cryptoWalletsList) {				
				if (wallet.getIdCrypto() == cryptoCurrency.getIdCrypto()) {					
					delta = cryptoCurrency.getCurrentPrice() - wallet.getPurchasePrice();
					cryptoCurrency.setDelta(delta);
					cryptoCurrenciesImpl.updateDelta(cryptoCurrency.getIdCrypto(), delta);
				}
			}
		}
		req.setAttribute("cryptoCurrenciesList", cryptoCurrenciesList);
		this.getServletContext().getRequestDispatcher("/WEB-INF/cryptocurrencies/list.jsp").forward(req, resp);
	}
}