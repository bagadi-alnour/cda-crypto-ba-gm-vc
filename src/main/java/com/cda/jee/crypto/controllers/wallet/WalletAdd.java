package com.cda.jee.crypto.controllers.wallet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

@WebServlet("/wallet-add")
public class WalletAdd extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		req.setAttribute("cryptoWallet", new CryptoWallet());
		CryptoCurrencyImpl cryptoCurrenciesImpl = new CryptoCurrencyImpl();
		List<CryptoCurrency> cryptoCurrenciesList = cryptoCurrenciesImpl.getAll();
		req.setAttribute("cryptoCurrenciesList", cryptoCurrenciesList);		
		this.getServletContext().getRequestDispatcher("/WEB-INF/wallets/wallet-add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		CryptoCurrencyImpl cryptoCurrenciesImpl = new CryptoCurrencyImpl();
		List<CryptoCurrency> cryptoCurrenciesList = cryptoCurrenciesImpl.getAll();
		CryptoWalletImpl cryptoWalletImpl = new CryptoWalletImpl();
		CryptoWallet cryptoWallet = new CryptoWallet();
		List<String> params = new ArrayList<String>(req.getParameterMap().keySet());
		for (String param : params) {
			if (!req.getParameter(param).isEmpty() && !req.getParameter(param).isBlank()) {
				switch (param) {
				case "cryptoCurrency":	
					cryptoWallet.setIdCrypto(Integer.parseInt(req.getParameter(param)));					
					break;
				case "purchasePrice":
					cryptoWallet.setPurchasePrice(Double.parseDouble(req.getParameter(param)));						
					break;
				case "quantity":
					cryptoWallet.setQuantity(Double.parseDouble(req.getParameter(param)));						
					break;
				case "purchaseDate":
					cryptoWallet.setPurchaseDate(LocalDateTime.parse(req.getParameter(param)));						
					break;
				default:
					break;
				}				
			}
		}
		cryptoWalletImpl.save(cryptoWallet);
		resp.sendRedirect(req.getContextPath() + "/wallets");
	}
}
