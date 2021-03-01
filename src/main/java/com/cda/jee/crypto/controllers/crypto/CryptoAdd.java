package com.cda.jee.crypto.controllers.crypto;

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
import com.cda.jee.crypto.model.CryptoCurrency;

@WebServlet("/crypto-add")
public class CryptoAdd extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		req.setAttribute("cryptoCurrency", new CryptoCurrency());
		this.getServletContext().getRequestDispatcher("/WEB-INF/cryptocurrencies/cryptocurrency-add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		CryptoCurrencyImpl cryptoCurrenciesImpl = new CryptoCurrencyImpl();
		CryptoCurrency cryptoCurrency = new CryptoCurrency();
		List<String> params = new ArrayList<String>(req.getParameterMap().keySet());
		for (String param : params) {
			if (!req.getParameter(param).isEmpty() && !req.getParameter(param).isBlank()) {
				switch (param) {
				case "name":
					cryptoCurrency.setName(req.getParameter(param));						
					break;
				case "symbol":
					cryptoCurrency.setSymbol(req.getParameter(param));						
					break;
				case "currentPrice":
					cryptoCurrency.setCurrentPrice(Double.parseDouble(req.getParameter(param)));						
					break;
				case "imageUrl":
					cryptoCurrency.setImageUrl(req.getParameter(param));						
					break;
				case "lastUpdated":
					cryptoCurrency.setLastUpdated(LocalDateTime.parse(req.getParameter(param)));						
					break;
				default:
					break;
				}				
			}
		}
		cryptoCurrenciesImpl.save(cryptoCurrency);
		resp.sendRedirect(req.getContextPath() + "/cryptocurrencies");
	}
}
